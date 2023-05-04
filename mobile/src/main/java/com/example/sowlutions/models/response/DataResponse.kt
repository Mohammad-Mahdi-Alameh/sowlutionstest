package com.example.sowlutions.models.response

import com.example.sowlutions.models.data.Product
import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("items") val products : MutableList<Product>,
    @SerializedName("offset") val offset : Int?
)
