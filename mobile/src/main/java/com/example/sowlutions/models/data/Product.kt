package com.example.sowlutions.models.data

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id") val id : Int,
    @SerializedName("title") val name : String?,
    @SerializedName("price") val price : String?,
    @SerializedName("images") val image : MutableList<ProductImage>?,
    @SerializedName("categories") val category : MutableList<ProductCategory>?,
    @SerializedName("quantity") val quantity : Int?
)
