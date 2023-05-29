package com.example.mazica.models.data

import com.google.gson.annotations.SerializedName

data class Parent (
    @SerializedName("counter") val counter : Int,
    @SerializedName("name") val name : String,
    @SerializedName("children") val children : MutableList<Task>?,
    )

