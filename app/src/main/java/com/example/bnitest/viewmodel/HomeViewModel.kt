package com.example.bnitest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.repository.MyRepository
import com.example.core.usecase.GetPromosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPromosUseCase: GetPromosUseCase
) : ViewModel() {

    fun getPromos() = getPromosUseCase.execute().asLiveData()



}