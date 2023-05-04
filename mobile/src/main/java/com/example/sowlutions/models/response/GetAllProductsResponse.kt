package com.example.sowlutions.models.response

import com.example.sowlutions.models.data.Product
import com.google.gson.annotations.SerializedName

data class GetAllProductsResponse(
    @SerializedName("data") val data : DataResponse,
    @SerializedName("success") val success : Boolean,
    @SerializedName("code") val code : String?,
    @SerializedName("message") val message : String?,
    )
