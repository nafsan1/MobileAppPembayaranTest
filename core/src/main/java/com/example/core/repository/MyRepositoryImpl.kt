package com.example.core.repository

import android.util.Log
import com.example.core.data.ErrorResponse
import com.example.core.data.portfolio.ChartData
import com.example.core.data.portfolio.ChartDataDeserializer
import com.example.core.data.portfolio.portfolioJsonItem
import com.example.core.data.promo.PromoResponseItem
import com.example.core.network.ApiResponse
import com.example.core.network.RemoteDataSource
import com.example.core.network.Resource
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val pref: PreferencesRepository
) : MyRepository {


    override fun getPromos(): Flow<Resource<MutableList<PromoResponseItem>>> {
        return flow {
            try {
                emit(Resource.Loading(isLoading = true))
                val data: MutableList<PromoResponseItem> =
                    when (val apiResponse = remoteDataSource.getPromos().first()) {
                        is ApiResponse.Success -> {
                            val response = apiResponse.data
                            val body = response.body()
                            body?.map {
                                it
                            }?.toMutableList() ?: mutableListOf()
                        }
                        is ApiResponse.Error -> {
                            mutableListOf()
                        }
                        else -> {
                            mutableListOf()
                        }
                    }
                emit(Resource.Success(data))
                emit(Resource.Loading(null, isLoading = false))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Loading(isLoading = false))
                emit(Resource.Error(e.message.toString(), null))
            }
        }
    }

    override fun getPortfolio(): Flow<Resource<List<ChartData>>> {
        return flow {
            try {
                emit(Resource.Loading(isLoading = true))
                val gson = GsonBuilder()
                    .registerTypeAdapter(ChartData::class.java, ChartDataDeserializer())
                    .create()

                val listType = object : TypeToken<List<ChartData>>() {}.type
                val chartData: List<ChartData> = gson.fromJson(portfolioJsonItem, listType)
                emit(Resource.Success(chartData))
                emit(Resource.Loading(null, isLoading = false))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Loading(isLoading = false))
                emit(Resource.Error(e.message.toString(), null))
            }
        }
    }
}