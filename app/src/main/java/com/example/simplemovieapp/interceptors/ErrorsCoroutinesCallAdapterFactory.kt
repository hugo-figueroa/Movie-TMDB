package com.example.simplemovieapp.interceptors

import com.example.networking.exceptions.NoInternetException
import com.example.networking.exceptions.NotFoundException
import com.example.networking.exceptions.ServerErrorException
import com.example.networking.exceptions.UnknownErrorException
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.net.HttpURLConnection
import javax.inject.Inject

/**
 * ErrorsCoroutinesCallAdapterFactory
 *
 * @author (c) 2024, Hugo Figueroa
 */
class ErrorsCoroutinesCallAdapterFactory @Inject constructor() : CallAdapter.Factory() {

    companion object {
        @JvmStatic
        @JvmName("create")
        operator fun invoke() = CoroutineCallAdapterFactory()
    }

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (Deferred::class.java != getRawType(returnType)) {
            return null
        }
        if (returnType !is ParameterizedType) {
            throw IllegalStateException(
                "Deferred return type must be parameterized as Deferred<Foo> or Deferred<out Foo>"
            )
        }
        val responseType = getParameterUpperBound(0, returnType)

        val rawDeferredType = getRawType(responseType)
        return if (rawDeferredType == Response::class.java) {
            if (responseType !is ParameterizedType) {
                throw IllegalStateException(
                    "Response must be parameterized as Response<Foo> or Response<out Foo>"
                )
            }
            ResponseCallAdapter<Any>(getParameterUpperBound(0, responseType))
        } else {
            BodyCallAdapter<Any>(responseType)
        }
    }

    private class BodyCallAdapter<T>(
        private val responseType: Type
    ) : CallAdapter<T, Deferred<T>> {

        override fun responseType() = responseType

        override fun adapt(call: Call<T>): Deferred<T> {
            val deferred = CompletableDeferred<T>()

            deferred.invokeOnCompletion {
                if (deferred.isCancelled) {
                    call.cancel()
                }
            }

            call.enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    deferred.completeExceptionally(
                        mapToDomainException(
                            t
                        )
                    )
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (response.isSuccessful) {
                        deferred.complete(response.body()!!)
                    } else {
                        deferred.completeExceptionally(
                            try {
                                mapToDomainException(
                                    HttpException(response), response
                                )
                            } catch (e: Exception) {
                                Exception(HttpException(response))
                            }
                        )
                    }
                }
            })

            return deferred
        }

        fun mapToDomainException(
            remoteException: Throwable,
            response: Response<T>? = null
        ): Exception {
            return when (remoteException) {
                is IOException -> NoInternetException()
                is HttpException ->
                    response?.let {
                        when (remoteException.code()) {
                            // not found
                            HttpURLConnection.HTTP_NOT_FOUND -> NotFoundException()
                            // unavailable service
                            HttpURLConnection.HTTP_UNAVAILABLE -> ServerErrorException()
                            else -> UnknownErrorException()
                        }

                    } ?: Exception(remoteException)

                else -> Exception(remoteException)
            }
        }
    }

    private class ResponseCallAdapter<T>(
        private val responseType: Type
    ) : CallAdapter<T, Deferred<Response<T>>> {

        override fun responseType() = responseType

        override fun adapt(call: Call<T>): Deferred<Response<T>> {
            val deferred = CompletableDeferred<Response<T>>()

            deferred.invokeOnCompletion {
                if (deferred.isCancelled) {
                    call.cancel()
                }
            }

            call.enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    deferred.completeExceptionally(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    deferred.complete(response)
                }
            })

            return deferred
        }
    }
}
