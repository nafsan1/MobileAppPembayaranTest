package com.example.core.di

import com.example.core.repository.MyRepository
import com.example.core.usecase.GetPortfolioUseCase
import com.example.core.usecase.GetPromosUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetPromosUseCase(myRepository: MyRepository): GetPromosUseCase {
        return GetPromosUseCase(myRepository)
    }

    @Singleton
    @Provides
    fun provideGetPortfolioUseCase(myRepository: MyRepository): GetPortfolioUseCase {
        return GetPortfolioUseCase(myRepository)
    }
}