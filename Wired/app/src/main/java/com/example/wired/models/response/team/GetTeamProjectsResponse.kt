package com.example.wired.models.response.team

import com.example.wired.models.data.Project
import com.google.gson.annotations.SerializedName

data class GetTeamProjectsResponse(
    @SerializedName("data") val projects : MutableList<Project>,
    @SerializedName("success") val success : Boolean,
    @SerializedName("id") val id : Int,
    @SerializedName("code") val code : String?,
    @SerializedName("message") val message : String?,
    )
