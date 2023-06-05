package com.example.bnitest.helper.qrcodescanner

import android.content.Intent
import com.google.zxing.integration.android.IntentIntegrator

interface QRCodeScanner {
    suspend fun createScanIntent(): IntentIntegrator
    fun handleScanResult(resultCode: Int, data: Intent?): String?
}