package com.example.bnitest.helper.qrcodescanner

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.example.bnitest.helper.CaptureActivityPortrait
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DefaultQRCodeScanner(private val activity: FragmentActivity) : QRCodeScanner {

    override suspend fun createScanIntent(): IntentIntegrator {
        return withContext(Dispatchers.IO) {
            val intentIntegrator = IntentIntegrator(activity)
            intentIntegrator.setOrientationLocked(true)
            intentIntegrator.setPrompt("Scan a QRIS")
            intentIntegrator.captureActivity = CaptureActivityPortrait::class.java
            intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            intentIntegrator
        }
    }

    override fun handleScanResult(resultCode: Int, data: Intent?): String? {
        if (resultCode == Activity.RESULT_OK) {
            val scanResult = IntentIntegrator.parseActivityResult(resultCode, data)
            return scanResult?.contents
        }
        return null
    }
}