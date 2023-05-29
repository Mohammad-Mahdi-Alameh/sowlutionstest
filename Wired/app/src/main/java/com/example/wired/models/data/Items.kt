package com.example.wired.models.data

import com.example.mazica.models.data.Task
import com.google.gson.annotations.SerializedName

data class Items (
    @SerializedName("items") val tasks : MutableList<Task>,
    )

