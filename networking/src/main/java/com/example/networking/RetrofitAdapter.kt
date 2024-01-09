package com.example.networking

import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit

/**
 * RetrofitAdapter
 *
 * @author (c) 2024, Hugo Figueroa
 */
object RetrofitAdapter {
    fun getRetrofitClient(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
        callAdapterFactory: CallAdapter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .client(okHttpClient)
            .build()
    }
}