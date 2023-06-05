package com.example.core.repository

import com.example.core.data.portfolio.ChartData
import com.example.core.data.promo.PromoResponseItem
import com.example.core.network.Resource
import kotlinx.coroutines.flow.Flow

interface MyRepository {
    fun getPromos(): Flow<Resource<MutableList<PromoResponseItem>>>
    fun getPortfolio(): Flow<Resource<List<ChartData>>>
}