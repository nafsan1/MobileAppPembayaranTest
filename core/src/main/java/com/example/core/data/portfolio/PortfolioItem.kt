package com.example.core.data.portfolio

import android.os.Parcelable
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.lang.reflect.Type

sealed class ChartData {

    data class DonutChart(
        val type: String,
        val data: MutableList<ChartDataItem>
    ) : ChartData()

    data class LineChart(
        val type: String,
        val data: LineChartDetail
    ) : ChartData()
}
@Parcelize
data class ChartDataItem(
    var label: String = "",
    var percentage: String = "",
    var data: MutableList<DataDetail> = arrayListOf()
):Parcelable

@Parcelize
data class DataDetail(
    val trx_date: String = "",
    val nominal: Int = 0
):Parcelable

data class LineChartDetail(
    val month: MutableList<Int>
)

class ChartDataDeserializer : JsonDeserializer<ChartData> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): ChartData {
        val jsonObject = json.asJsonObject

        return when (jsonObject.get("type").asString) {
            "donutChart" -> context.deserialize<ChartData.DonutChart>(json, ChartData.DonutChart::class.java)
            "lineChart" -> context.deserialize<ChartData.LineChart>(json, ChartData.LineChart::class.java)
            else -> throw JsonParseException("Unsupported type of chart data")
        }
    }
}
