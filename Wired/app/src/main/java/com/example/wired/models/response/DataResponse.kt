package com.example.mazica.models.response

import com.example.mazica.models.data.Task
import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("items") val products : MutableList<Task>,
    @SerializedName("offset") val offset : Int?
)
