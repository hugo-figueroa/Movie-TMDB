package com.example.networking

import com.example.networking.MovieTMDBOkHttpClient.getOkHttpClient
import com.google.gson.Gson
import okhttp3.Interceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * MovieTMDBNetworking
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class MovieTMDBNetworking private constructor(
    private val urlBase: String,
    private val interceptorList: List<Interceptor>? = null,
    private var gson: Gson? = null,
    private var connectTimeout: Long?,
    private var readTimeout: Long?,
    private var writeTimeout: Long?,
    private var enableDebugLogs: Boolean?,
    private var converterFactory: Converter.Factory? = null,
    private var callAdapterFactory: CallAdapter.Factory = RxJava2CallAdapterFactory.create()
) {

    data class Builder(
        private var urlBase: String,
        private var interceptorList: List<Interceptor>? = null,
        private var gson: Gson? = null,
        private var connectTimeout: Long? = null,
        private var readTimeout: Long? = null,
        private var writeTimeout: Long? = null,
        private var enableDebugLogs: Boolean? = null,
        private var converterFactory: Converter.Factory? = null,
        private var callAdapterFactory: CallAdapter.Factory = RxJava2CallAdapterFactory.create()
    ) {

        fun interceptorList(interceptorList: List<Interceptor>) =
            apply { this.interceptorList = interceptorList }

        fun gson(gson: Gson?) = apply { this.gson = gson }
        fun connectTimeout(connectTimeout: Long) = apply { this.connectTimeout = connectTimeout }
        fun readTimeout(readTimeout: Long) = apply { this.readTimeout = readTimeout }
        fun writeTimeout(writeTimeout: Long) = apply { this.writeTimeout = writeTimeout }
        fun enableDebugLogs(enableDebugLogs: Boolean) =
            apply { this.enableDebugLogs = enableDebugLogs }

        fun converterFactory(converterFactory: Converter.Factory) = apply {
            this.converterFactory = converterFactory
        }

        fun callAdapterFactory(callAdapterFactory: CallAdapter.Factory) = apply {
            this.callAdapterFactory = callAdapterFactory
        }

        fun buildRetrofitClient() = MovieTMDBNetworking(
            urlBase,
            interceptorList,
            gson,
            connectTimeout,
            readTimeout,
            writeTimeout,
            enableDebugLogs,
            converterFactory,
            callAdapterFactory
        ).getRetrofitClient()
    }

    private fun getRetrofitClient(): Retrofit {
        return RetrofitAdapter.getRetrofitClient(
            urlBase,
            getOkHttpClient(
                interceptorList,
                connectTimeout,
                readTimeout,
                writeTimeout,
                enableDebugLogs
            ),
            getConverterFactory(),
            callAdapterFactory
        )
    }

    private fun getConverterFactory(): Converter.Factory {
        return converterFactory ?: run {
            GsonConverterFactory.create(gson ?: Gson())
        }
    }
}