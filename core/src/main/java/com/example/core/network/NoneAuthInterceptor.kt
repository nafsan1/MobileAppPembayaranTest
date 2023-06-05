package com.example.core.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NoneAuthInterceptor  @Inject constructor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authorisedRequestBuilder = originalRequest.newBuilder()
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
        return chain.proceed(authorisedRequestBuilder.build())
    }
}