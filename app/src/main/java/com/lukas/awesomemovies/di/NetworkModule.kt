package com.lukas.awesomemovies.di

import com.lukas.awesomemovies.data.Constants
import com.lukas.awesomemovies.data.network.MoviesService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { retrofit.create(MoviesService::class.java) }
}

private val httpClient = OkHttpClient.Builder()
    .addInterceptor(object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            chain.run {
                val newUrl = request().url
                    .newBuilder()
                    .addQueryParameter("api_key", "51febce71b7b5e924a2ba54169f57a2e")
                    .build()
                request()
                    .newBuilder()
                    .url(newUrl)
                    .build()
                    .let {
                        return chain.proceed(it)
                    }
            }
        }
    })
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(Constants.MOVIES_DB_BASE_URL)
    .client(httpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()
