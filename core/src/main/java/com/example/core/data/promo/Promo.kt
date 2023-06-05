package com.example.core.data.promo

data class Promo (
    var title: String? = "",
    var alt: String,
    var count: Int,
    var createdAt: String,
    var desc: String,
    var descPromo: String? = "",
    var id: Int,
    var img: String? = "",
    var latitude: String,
    var lokasi: String,
    var longitude: Double,
    var nama: String,
    var namePromo: String,
    var publishedAt: String,
    var updatedAt: String
)