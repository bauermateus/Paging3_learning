package com.mbs.paging

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitConfig {

    companion object {

        private lateinit var retrofit: Retrofit

        private fun getRetrofitInstance(): Retrofit {

            val client = OkHttpClient.Builder().build()

            if (!::retrofit.isInitialized) {
                val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
                retrofit = Retrofit.Builder().baseUrl("https://api.thecatapi.com/v1/images/").client(client)
                    .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
            }
            return retrofit
        }

        fun <S> createService(serviceClass: Class<S>): S {
            return getRetrofitInstance().create(serviceClass)
        }
    }
}
