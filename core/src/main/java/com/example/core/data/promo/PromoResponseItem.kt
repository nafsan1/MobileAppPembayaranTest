package com.example.core.data.promo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PromoResponseItem(
    @field:SerializedName("Title")
    val title: String? = "",
    @field:SerializedName("alt")
    val alt: String,
    @field:SerializedName("count")
    val count: Int,
    @field:SerializedName("createdAt")
    val createdAt: String,
    @field:SerializedName("created_at")
    val created_at: String,
    @field:SerializedName("desc")
    val desc: String,
    @field:SerializedName("desc_promo")
    val descPromo: String? = "",
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("img")
    val img: Img,
    @field:SerializedName("latitude")
    val latitude: String,
    @field:SerializedName("lokasi")
    val lokasi: String,
    @field:SerializedName("longitude")
    val longitude: String,
    @field:SerializedName("nama")
    val nama: String,
    @field:SerializedName("name_promo")
    val namePromo: String? = "",
    @field:SerializedName("published_at")
    val publishedAt: String,
    @field:SerializedName("updated_at")
    val updatedAt: String
):Parcelable
