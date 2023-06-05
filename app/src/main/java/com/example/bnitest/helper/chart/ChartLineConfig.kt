package com.example.bnitest.helper.chart

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.example.bnitest.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class ChartLineConfig(private val context: Context, private val chart: LineChart) {
    fun config(list: ArrayList<Entry>) {
        setDataChart(list)
        setupXAxis()
        setupLeftAxis()
        setupRightAxis()
        setupMarker(list)
        setupChartProperties()
    }

    private fun setDataChart(entry: ArrayList<Entry>) {
        val lineDataSet = LineDataSet(entry, "")
        lineDataSet.setColors(ContextCompat.getColor(context, R.color.red))
        lineDataSet.lineWidth = 1f
        lineDataSet.setDrawValues(false)
        lineDataSet.setDrawCircleHole(false)
        lineDataSet.setDrawCircles(false)
        lineDataSet.highLightColor =
            ContextCompat.getColor(context, R.color.grey_lighten_32)
        lineDataSet.enableDashedHighlightLine(20f, 10f, 0f)

        val data = LineData(lineDataSet)
        data.notifyDataChanged()
        chart.data = data
        chart.invalidate()
    }

    private fun setupXAxis() {
        chart.xAxis.apply {
            position = XAxis.XAxisPosition.TOP
            setDrawLabels(false)
            axisLineWidth = 2F
            axisLineColor = Color.WHITE
            textColor = Color.WHITE
            setDrawGridLines(false)
        }
    }

    private fun setupLeftAxis() {
        chart.axisLeft.apply {
            axisLineWidth = 2F
            axisLineColor = Color.WHITE
            textColor = Color.WHITE
            setDrawGridLines(false)
            gridLineWidth = 2f
        }
    }

    private fun setupRightAxis() {
        chart.axisRight.apply {
            axisLineColor = Color.WHITE
            textColor = ContextCompat.getColor(context, R.color.colorGray02)
            setDrawGridLines(false)
            isEnabled = true
            setLabelCount(6, true)
        }
    }

    private fun setupMarker(list: ArrayList<Entry>) {
        val markerData = ArrayList<String>()
        for (i in list) {
            markerData.add(i.toString())
        }

        val marker = LinePortfolioMarker(context, markerData, R.layout.marker_portfolio)
        marker.chartView = chart
        chart.marker = marker
    }

    private fun setupChartProperties() {
        chart.legend.apply {
            isEnabled = false
        }

        chart.description.isEnabled = false

        chart.setPinchZoom(false)
        chart.setTouchEnabled(true)
        chart.setScaleEnabled(false)
        chart.isDoubleTapToZoomEnabled = false
        chart.isDragEnabled = true
        chart.animate().duration = 2000
    }

}