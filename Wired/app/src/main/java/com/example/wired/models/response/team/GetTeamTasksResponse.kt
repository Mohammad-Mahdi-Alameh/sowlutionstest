package com.example.wired.models.response.team

import com.example.mazica.models.response.DataResponse
import com.google.gson.annotations.SerializedName

data class GetTeamTasksResponse(
    @SerializedName("data") val data : DataResponse,
    @SerializedName("success") val success : Boolean,
    @SerializedName("code") val code : String?,
    @SerializedName("message") val message : String?,
    )
