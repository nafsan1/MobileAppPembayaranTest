package com.example.core.usecase

import com.example.core.data.portfolio.ChartData
import com.example.core.network.Resource
import com.example.core.repository.MyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPortfolioUseCase @Inject constructor(private val myRepository: MyRepository) {
    fun execute(): Flow<Resource<List<ChartData>>> {
        return myRepository.getPortfolio()
    }
}