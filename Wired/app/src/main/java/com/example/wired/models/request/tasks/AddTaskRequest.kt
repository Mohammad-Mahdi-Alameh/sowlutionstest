package com.example.wired.models.request.tasks

import com.example.mazica.models.response.DataResponse
import com.google.gson.annotations.SerializedName

data class AddTaskRequest(
    @SerializedName("project") val project : String,
    @SerializedName("category") val category : String,
    @SerializedName("notes") val notes : String,
    @SerializedName("due_date") val date : String?,
    @SerializedName("estimated_time_hours") val totalHours : Int?,
    @SerializedName("estimated_time_minutes") val totalMinutes : Int?,
    @SerializedName("offset") val offset : Int,
    @SerializedName("limit") val limit : Int,
    )
