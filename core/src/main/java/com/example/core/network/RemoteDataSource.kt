package com.example.core.network


import com.example.core.data.promo.PromoResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class RemoteDataSource @Inject constructor(
    private val apiServiceNotAuth: ApiServiceNotAuth
) {
    suspend fun getPromos(): Flow<ApiResponse<Response<MutableList<PromoResponseItem>>>> {
        return flow {
            try {
                val response = apiServiceNotAuth.getPromos()
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}