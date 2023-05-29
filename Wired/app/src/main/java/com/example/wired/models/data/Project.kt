package com.example.wired.models.data

import com.google.gson.annotations.SerializedName

data class Project(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("task_categories") val tasks : MutableList<TaskCategories>,
)
