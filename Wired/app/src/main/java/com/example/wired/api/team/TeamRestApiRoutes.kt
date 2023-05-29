package com.example.wired.api.team

import com.example.wired.api.PublicHeaders
import com.example.wired.models.response.team.GetTeamProjectsResponse
import com.example.wired.models.response.team.GetTeamTasksResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Path

interface TeamRestApiRoutes {

    @GET("team/tasks")
    fun getTeamTasks(
        @HeaderMap getPublicHeaders: PublicHeaders
    ): Call<GetTeamTasksResponse>

    @GET("team/projects")
    fun getTeamProjects(
        @HeaderMap getPublicHeaders: PublicHeaders,
    ): Call <GetTeamProjectsResponse>

}