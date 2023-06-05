package com.example.core.di

import android.content.Context
import android.content.SharedPreferences
import com.example.core.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MyPreferenceModule {

    @Provides
    fun provideSharedPreferences(@ApplicationContext app: Context): SharedPreferences =
        app.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
}