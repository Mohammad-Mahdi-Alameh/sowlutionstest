package com.example.sowlutions.models.data

import com.google.gson.annotations.SerializedName

data class ProductImage(
    @SerializedName("id") val id : Int,
    @SerializedName("thumbnail") val thumbnailImage : String?,
    @SerializedName("large") val largeImage : String?,
)
