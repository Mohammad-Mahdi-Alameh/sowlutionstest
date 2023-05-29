package com.example.mazica.models.data

import com.example.wired.models.data.Project
import com.example.wired.models.data.TaskCategories
import com.google.gson.annotations.SerializedName

data class Task(
    @SerializedName("id") val id : Int,
    @SerializedName("due_date") val dueDate : String?,
    @SerializedName("estimated_time_hours") val estHours : Int,
    @SerializedName("estimated_time_minutes") val estMins : Int,
    @SerializedName("total_spent_time") val totalTime : String,
    @SerializedName("notes") val notes : String,
    @SerializedName("project") val project : Project,
    @SerializedName("task_category") val category : TaskCategories
)
