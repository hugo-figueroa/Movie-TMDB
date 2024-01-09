package com.example.networking

import java.util.concurrent.TimeUnit
import okhttp3.CipherSuite.Companion.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256
import okhttp3.CipherSuite.Companion.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256
import okhttp3.CipherSuite.Companion.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256
import okhttp3.ConnectionSpec
import okhttp3.ConnectionSpec.Companion.MODERN_TLS
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor


/**
 * MovieTMDBOkHttpClient
 *
 * @author (c) 2024, Hugo Figueroa
 */
object MovieTMDBOkHttpClient {

    private lateinit var httpClient: OkHttpClient.Builder

    private const val connectTimeout: Long = 30
    private const val readTimeout: Long = 60
    private const val writeTimeout: Long = 60

    fun getOkHttpClient(
        interceptors: List<Interceptor>?,
        connectTimeout: Long?,
        readTimeout: Long?,
        writeTimeout: Long?,
        enableDebugLogs: Boolean?
    ): OkHttpClient {

        httpClient = OkHttpClient.Builder()
            .connectTimeout(connectTimeout ?: this.connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout ?: this.readTimeout, TimeUnit.SECONDS)
            .writeTimeout(writeTimeout ?: this.writeTimeout, TimeUnit.SECONDS)

        addInterceptors(enableDebugLogs, interceptors)

        return httpClient.build()
    }

    private fun addInterceptors(enableDebugLogs: Boolean?, interceptors: List<Interceptor>?) {
        interceptors?.let { list ->
            for (it in list) {
                httpClient.addInterceptor(it)
            }
        }
        httpClient.addInterceptor(getHttpLoggingInterceptor(enableDebugLogs ?: false))
    }

    private fun getHttpLoggingInterceptor(enableDebugLogs: Boolean): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (enableDebugLogs) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return interceptor
    }
}
