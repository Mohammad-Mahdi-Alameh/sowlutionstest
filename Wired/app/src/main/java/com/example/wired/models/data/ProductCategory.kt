package com.example.mazica.models.data

import com.google.gson.annotations.SerializedName

data class ProductCategory(
    @SerializedName("id") val id : Int,
    @SerializedName("title") val categoryName : String?
)
