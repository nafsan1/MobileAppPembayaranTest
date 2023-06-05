package com.example.bnitest.helper.chart

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.widget.TextView
import com.example.bnitest.R
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight

@SuppressLint("ViewConstructor")
class LinePortfolioMarker(context: Context, val date: ArrayList<String>, layouId: Int) :
    MarkerView(context, layouId) {

    var  uiScreenWidth = getResources().getDisplayMetrics().widthPixels

    override fun draw(canvas: Canvas, posx: Float, posy: Float) {
        var posx1 = posx
        val posy1 = posy / 1.5f

        val w = width
        if ((uiScreenWidth - posx1 -w) < w){
            posx1 -= w
        }
        canvas.translate(posx1,posy1)
        draw(canvas)
        canvas.translate(-posx1, posy1)
    }

    override fun refreshContent(e: Entry?, highlight: Highlight?) {

        if (e == null) {
            return
        }
        val txtMonth = findViewById<TextView>(R.id.txtMonth)
        val dateId = e.x.toInt()

        txtMonth.text = e.y.toInt().toString() + " Month"

        super.refreshContent(e, highlight)

    }
}