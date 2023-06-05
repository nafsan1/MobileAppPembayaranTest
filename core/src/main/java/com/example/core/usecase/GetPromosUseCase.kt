package com.example.core.usecase

import com.example.core.data.promo.PromoResponseItem
import com.example.core.network.Resource
import com.example.core.repository.MyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPromosUseCase @Inject constructor(private val myRepository: MyRepository) {
    fun execute(): Flow<Resource<MutableList<PromoResponseItem>>> {
        return myRepository.getPromos()
    }
}
