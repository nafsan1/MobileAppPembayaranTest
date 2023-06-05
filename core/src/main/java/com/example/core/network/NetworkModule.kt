package com.example.core.network

import com.example.core.util.Constants.BASE_URL
import com.example.core.util.Constants.CONNECTION_TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    //RETROFIT No Auth
    fun provideOkHttpClientNoAuth(
        noneAuthInterceptor: NoneAuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addNetworkInterceptor(noneAuthInterceptor)
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideApiServiceNoAuth(
        noneAuthInterceptor: NoneAuthInterceptor
    ): ApiServiceNotAuth {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClientNoAuth(noneAuthInterceptor))
            .build()
        return retrofit.create(ApiServiceNotAuth::class.java)
    }


}