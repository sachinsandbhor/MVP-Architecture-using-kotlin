package com.sachinsandbhor.retrofitkotlin.network

import com.sachinsandbhor.retrofitkotlin.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Sachin.Sandbhor on 08-03-2018.
 */
class MovieService {

    companion object {
        private var retrofit: Retrofit? = null
        fun getClient(): Retrofit {
            if (null == retrofit) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                val httpClient = OkHttpClient.Builder()
                httpClient.addInterceptor(logging)  // <-- this is the important line!

                retrofit = Retrofit.Builder()
                        .baseUrl(BuildConfig.BASEURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(httpClient.build())
                        .build()
            }
            return retrofit as Retrofit
        }
    }



}