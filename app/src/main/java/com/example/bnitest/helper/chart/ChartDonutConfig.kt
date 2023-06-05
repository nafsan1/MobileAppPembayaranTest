package com.example.bnitest.helper.chart

import android.content.Context
import android.graphics.Color
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

class ChartDonutConfig(private val context: Context, private val chart: PieChart) {

    fun config(list: ArrayList<PieEntry>) {
        setupLegend()
        setDataChart(list)
    }

    private fun setupLegend() {
        val l = chart.legend
        l.isEnabled = false
        chart.description.text = ""
        chart.centerText = "Portfolio"
    }

    private fun setDataChart(list: ArrayList<PieEntry>) {
        val pieDataSet = PieDataSet(list, "")

        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS, 255)
        pieDataSet.valueTextColor = Color.BLACK
        pieDataSet.valueTextSize = 15f

        val pieData = PieData(pieDataSet)
        chart.data = pieData
        chart.animateY(1000)
    }
}