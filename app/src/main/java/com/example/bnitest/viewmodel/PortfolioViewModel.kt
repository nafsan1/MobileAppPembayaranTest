package com.example.bnitest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.repository.MyRepository
import com.example.core.usecase.GetPortfolioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val getPortfolioUseCase: GetPortfolioUseCase
) : ViewModel() {

    fun getPortfolio() = getPortfolioUseCase.execute().asLiveData()



}