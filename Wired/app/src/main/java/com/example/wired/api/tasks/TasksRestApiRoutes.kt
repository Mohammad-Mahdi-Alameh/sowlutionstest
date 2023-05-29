package com.example.wired.api.tasks

import com.example.wired.api.PublicHeaders
import com.example.wired.models.request.tasks.AddTaskRequest
import com.example.wired.models.response.tasks.AddTaskResponse
import com.example.wired.models.response.tasks.GetTasksResponse
import com.example.wired.models.response.tasks.TaskByIdResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TasksRestApiRoutes {

    @GET("tasks?offset=10")
    fun getTasks(
        @HeaderMap getPublicHeaders: PublicHeaders
    ): Call<GetTasksResponse>

    @POST("tasks")
    fun addTask(
        @HeaderMap getPublicHeaders: PublicHeaders,
        @Body addTaskRequest: AddTaskRequest
    ): Call<AddTaskResponse>

    @POST("tasks/{id}/start")
    fun startTask(
        @HeaderMap getPublicHeaders: PublicHeaders,
        @Path("id") taskId : Int,
    ): Call <TaskByIdResponse>

    @POST("tasks/{id}/stop")
    fun stopTask(
        @HeaderMap getPublicHeaders: PublicHeaders,
        @Path("id") taskId : Int,
    ): Call <TaskByIdResponse>
    @PUT("tasks/{id}/end")
    fun endTask(
        @HeaderMap getPublicHeaders: PublicHeaders,
        @Path("id") taskId : Int,
    ): Call <TaskByIdResponse>

}