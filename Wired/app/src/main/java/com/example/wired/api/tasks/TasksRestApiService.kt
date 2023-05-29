package com.example.wired.api.tasks

import com.example.wired.api.PublicHeaders
import com.example.wired.api.ServiceBuilder
import com.example.wired.models.request.tasks.AddTaskRequest
import com.example.wired.models.response.tasks.AddTaskResponse
import com.example.wired.models.response.tasks.GetTasksResponse
import com.example.wired.models.response.tasks.TaskByIdResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class TasksRestApiService {
    val service = ServiceBuilder.buildService(TasksRestApiRoutes::class.java)

    fun getTasks(
        publicHeaders: PublicHeaders,
        onResult: (GetTasksResponse?) -> Unit
    ){
        service.getTasks(publicHeaders).enqueue(
            object : Callback<GetTasksResponse> {
                    override fun onFailure(call : Call<GetTasksResponse> ,t:Throwable){
                        val response = (t as? HttpException)?.response()
                        val statusCode = response?.code()
                        val errorMessage = response?.errorBody()?.string()
                        onResult(null)
                    }
                    override fun onResponse(
                        call : Call<GetTasksResponse> ,
                        response: Response<GetTasksResponse>
                    ){
                        val response  = response.body()
                        onResult(response)
                    }
            }
        )
    }
    fun addTask(
        publicHeaders: PublicHeaders,
        addTaskRequest: AddTaskRequest,
        onResult: (AddTaskResponse?) -> Unit
    ){
        service.addTask(publicHeaders,addTaskRequest).enqueue(
            object : Callback<AddTaskResponse> {
                    override fun onFailure(call : Call<AddTaskResponse>, t:Throwable){
                        val response = (t as? HttpException)?.response()
                        val statusCode = response?.code()
                        val errorMessage = response?.errorBody()?.string()
                        onResult(null)
                    }
                    override fun onResponse(
                        call : Call<AddTaskResponse>,
                        response: Response<AddTaskResponse>
                    ){
                        val response  = response.body()
                        onResult(response)
                    }
            }
        )
    }
    fun startTask(
        publicHeaders: PublicHeaders,
        taskId: Int,
        onResult: (TaskByIdResponse?) -> Unit
    ){
        service.startTask(publicHeaders,taskId).enqueue(
            object : Callback<TaskByIdResponse> {
                    override fun onFailure(call : Call<TaskByIdResponse>, t:Throwable){
                        val response = (t as? HttpException)?.response()
                        val statusCode = response?.code()
                        val errorMessage = response?.errorBody()?.string()
                        onResult(null)
                    }
                    override fun onResponse(
                        call : Call<TaskByIdResponse>,
                        response: Response<TaskByIdResponse>
                    ){
                        val response  = response.body()
                        onResult(response)
                    }
            }
        )
    }
    fun stopTask(
        publicHeaders: PublicHeaders,
        taskId: Int,
        onResult: (TaskByIdResponse?) -> Unit
    ){
        service.stopTask(publicHeaders,taskId).enqueue(
            object : Callback<TaskByIdResponse> {
                    override fun onFailure(call : Call<TaskByIdResponse>, t:Throwable){
                        val response = (t as? HttpException)?.response()
                        val statusCode = response?.code()
                        val errorMessage = response?.errorBody()?.string()
                        onResult(null)
                    }
                    override fun onResponse(
                        call : Call<TaskByIdResponse>,
                        response: Response<TaskByIdResponse>
                    ){
                        val response  = response.body()
                        onResult(response)
                    }
            }
        )
    }
    fun endTask(
        publicHeaders: PublicHeaders,
        taskId: Int,
        onResult: (TaskByIdResponse?) -> Unit
    ){
        service.endTask(publicHeaders,taskId).enqueue(
            object : Callback<TaskByIdResponse> {
                    override fun onFailure(call : Call<TaskByIdResponse>, t:Throwable){
                        val response = (t as? HttpException)?.response()
                        val statusCode = response?.code()
                        val errorMessage = response?.errorBody()?.string()
                        onResult(null)
                    }
                    override fun onResponse(
                        call : Call<TaskByIdResponse>,
                        response: Response<TaskByIdResponse>
                    ){
                        val response  = response.body()
                        onResult(response)
                    }
            }
        )
    }

}