package com.example.core.data.promo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Small(
    @field:SerializedName("ext")
    val ext: String,
    @field:SerializedName("hash")
    val hash: String,
    @field:SerializedName("height")
    val height: Int,
    @field:SerializedName("mime")
    val mime: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("path")
    val path: String? = "",
    @field:SerializedName("size")
    val size: Double,
    @field:SerializedName("url")
    val url: String,
    @field:SerializedName("width")
    val width: Int
):Parcelable