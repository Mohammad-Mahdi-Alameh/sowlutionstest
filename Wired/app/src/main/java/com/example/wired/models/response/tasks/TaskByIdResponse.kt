package com.example.wired.models.response.tasks

import com.example.mazica.models.data.Task
import com.google.gson.annotations.SerializedName

data class TaskByIdResponse(
    @SerializedName("data") val data : Task,
    @SerializedName("success") val success : Boolean,
    @SerializedName("code") val code : String?,
    @SerializedName("message") val message : String?,
    )
