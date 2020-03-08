package com.beok.common.di

import com.beok.common.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TOKEN = "c4563edd686c3f1aeb4bcf120d85450df811d2e8"

fun getRetrofitBasicModule(url: String) = module {
    factory {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }
    factory {
        Interceptor {
            it.proceed(
                it.request()
                    .newBuilder()
                    .addHeader("Authorization", "token $TOKEN")
                    .build()
            )
        }
    }
    factory { StethoInterceptor() }
    factory {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(get())
            .addNetworkInterceptor(get<StethoInterceptor>())
            .build()
    }
    factory { GsonConverterFactory.create() }
    single {
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(get<GsonConverterFactory>())
            .client(get())
            .build()
    }
}