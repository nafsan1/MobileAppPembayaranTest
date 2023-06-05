package com.example.core.network


import com.example.core.data.promo.PromoResponseItem
import com.example.core.util.Constants.URL_PROMO
import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceNotAuth {

    @GET(URL_PROMO)
    suspend fun getPromos(): Response<MutableList<PromoResponseItem>>
}