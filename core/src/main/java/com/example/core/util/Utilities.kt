package com.example.core.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

object Utilities {

    fun Any.formatRupiah(): String {
        val symbols = DecimalFormatSymbols(Locale("in", "ID"))
        symbols.groupingSeparator = '.'
        symbols.decimalSeparator = ','
        val formatter = DecimalFormat("Rp ###,###", symbols)
        return formatter.format(this)
    }
}