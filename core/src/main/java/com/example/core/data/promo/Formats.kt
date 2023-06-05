package com.example.core.data.promo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Formats(
    @field:SerializedName("medium")
    val medium: Medium,
    @field:SerializedName("small")
    val small: Small,
    @field:SerializedName("thumbnail")
    val thumbnail: Thumbnail
): Parcelable