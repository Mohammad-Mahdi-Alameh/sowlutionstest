package com.example.sowlutions.models.response

import com.example.sowlutions.models.data.Product
import com.google.gson.annotations.SerializedName

data class GetProductByIdResponse(
    @SerializedName("data") val product : Product,
    @SerializedName("success") val success : Boolean,
    @SerializedName("code") val code : String?,
    @SerializedName("message") val message : String?,
    )
