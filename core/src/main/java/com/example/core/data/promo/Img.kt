package com.example.core.data.promo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Img(
    @field:SerializedName("alternativeText")
    val alternativeText: String,
    @field:SerializedName("caption")
    val caption: String? = "",
    @field:SerializedName("created_at")
    val created_at: String,
    @field:SerializedName("ext")
    val ext: String,
    @field:SerializedName("hash")
    val hash: String,
    @field:SerializedName("height")
    val height: Int,
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("mime")
    val mime: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("previewUrl")
    val previewUrl: String? = "",
    @field:SerializedName("provider")
    val provider: String,
    @field:SerializedName("provider_metadata")
    val providerMetadata: String? = "",
    @field:SerializedName("size")
    val size: Double,
    @field:SerializedName("updated_at")
    val updatedAt: String,
    @field:SerializedName("url")
    val url: String,
    @field:SerializedName("width")
    val width: Int? =0
):Parcelable