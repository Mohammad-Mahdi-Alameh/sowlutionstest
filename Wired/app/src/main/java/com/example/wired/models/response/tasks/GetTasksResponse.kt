package com.example.wired.models.response.tasks

import com.example.wired.models.data.Items
import com.google.gson.annotations.SerializedName

data class GetTasksResponse(
    @SerializedName("data") val data : Items,
    @SerializedName("success") val success : Boolean,
    @SerializedName("code") val code : String?,
    @SerializedName("message") val message : String?,
    )
