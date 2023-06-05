package com.example.core.di

import com.example.core.network.NetworkModule
import com.example.core.network.RemoteDataSource
import com.example.core.repository.MyRepository
import com.example.core.repository.MyRepositoryImpl
import com.example.core.repository.PreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module(includes = [NetworkModule::class, MyPreferenceModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Binds
    abstract fun provideRepository(repository: MyRepositoryImpl): MyRepository


}

@Module(includes = [NetworkModule::class, MyPreferenceModule::class])
@InstallIn(ViewModelComponent::class)
internal object ViewModelMovieModule {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Provides
    @ViewModelScoped
    fun provideRepo(
        remoteDataSource: RemoteDataSource,
        pref: PreferencesRepository
    ) =
        MyRepositoryImpl(remoteDataSource, pref);
}