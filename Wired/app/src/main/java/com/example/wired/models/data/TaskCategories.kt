package com.example.wired.models.data

import com.example.mazica.models.data.ProductCategory
import com.example.mazica.models.data.ProductImage
import com.google.gson.annotations.SerializedName

data class TaskCategories(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
)
