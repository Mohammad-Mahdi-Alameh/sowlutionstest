package com.example.wired.api.team

import android.util.Log
import com.example.wired.api.PublicHeaders
import com.example.wired.api.ServiceBuilder
import com.example.wired.models.response.team.GetTeamProjectsResponse
import com.example.wired.models.response.team.GetTeamTasksResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class TeamRestApiService {
    val service = ServiceBuilder.buildService(TeamRestApiRoutes::class.java)

    fun getTeamTasks(
        publicHeaders: PublicHeaders,
        onResult: (GetTeamTasksResponse?) -> Unit
    ){
        service.getTeamTasks(publicHeaders).enqueue(
            object : Callback<GetTeamTasksResponse> {
                    override fun onFailure(call : Call<GetTeamTasksResponse>, t:Throwable){
                        val response = (t as? HttpException)?.response()
                        val statusCode = response?.code()
                        val errorMessage = response?.errorBody()?.string()
                        Log.e("Fetching All Products"," API call failed with status code $statusCode and error message $errorMessage")
                        onResult(null)
                    }
                    override fun onResponse(
                        call : Call<GetTeamTasksResponse>,
                        response: Response<GetTeamTasksResponse>
                    ){
                        Log.i("Fetching All Products","Products fetched successfully")
                        val response  = response.body()
                        onResult(response)
                    }
            }
        )
    }

    fun getTeamProjects(
        publicHeaders: PublicHeaders,
        onResult:(GetTeamProjectsResponse?) -> Unit
    ){
        service.getTeamProjects(publicHeaders).enqueue(
            object : Callback<GetTeamProjectsResponse>{
                override fun onFailure(call: Call<GetTeamProjectsResponse>, t: Throwable) {
                    val response = (t as? HttpException)?.response()
                    val statusCode = response?.code()
                    val errorMessage = response?.errorBody()?.string()
                    Log.e("Fetch Product"," API call failed with status code $statusCode and error message $errorMessage")
                    onResult(null)
                }
                override fun onResponse(call: Call<GetTeamProjectsResponse>, response: Response<GetTeamProjectsResponse>) {
                    Log.i("Fetch  Product","Products fetched successfully")
                    val response = response.body()
                    onResult(response)
                }
            }
        )
    }
}