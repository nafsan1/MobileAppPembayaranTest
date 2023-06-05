package com.example.bnitest.di

import android.app.Activity
import android.content.Context
import com.example.bnitest.helper.qrcodescanner.DefaultQRCodeScanner
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleMain {

}