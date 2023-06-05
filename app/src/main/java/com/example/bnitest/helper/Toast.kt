package com.example.bnitest.helper

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

object Toast {

    fun String.toast(context: Context) {
        Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
    }

}