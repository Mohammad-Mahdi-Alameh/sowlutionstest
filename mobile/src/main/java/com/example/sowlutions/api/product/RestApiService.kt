package com.example.sowlutions.api.product

import android.util.Log
import com.example.sowlutions.api.PublicHeaders
import com.example.sowlutions.api.ServiceBuilder
import com.example.sowlutions.models.data.Product
import com.example.sowlutions.models.response.GetAllProductsResponse
import com.example.sowlutions.models.response.GetProductByIdResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class RestApiService {
    val service = ServiceBuilder.buildService(RestApiRoutes::class.java)

    fun getAllTodos(
        publicHeaders: PublicHeaders,
        onResult: (GetAllProductsResponse?) -> Unit
    ){
        service.getAllProducts(publicHeaders).enqueue(
            object : Callback<GetAllProductsResponse> {
                    override fun onFailure(call : Call<GetAllProductsResponse> ,t:Throwable){
                        val response = (t as? HttpException)?.response()
                        val statusCode = response?.code()
                        val errorMessage = response?.errorBody()?.string()
                        Log.e("Fetching All Products"," API call failed with status code $statusCode and error message $errorMessage")
                        onResult(null)
                    }
                    override fun onResponse(
                        call : Call<GetAllProductsResponse> ,
                        response: Response<GetAllProductsResponse>
                    ){
                        Log.i("Fetching All Products","Products fetched successfully")
                        val response  = response.body()
                        onResult(response)
                    }
            }
        )
    }

    fun getProductById(
        publicHeaders: PublicHeaders,
        todoId:Int,
        onResult:(GetProductByIdResponse?) -> Unit
    ){
        service.getProductById(publicHeaders,todoId).enqueue(
            object : Callback<GetProductByIdResponse>{
                override fun onFailure(call: Call<GetProductByIdResponse>, t: Throwable) {
                    val response = (t as? HttpException)?.response()
                    val statusCode = response?.code()
                    val errorMessage = response?.errorBody()?.string()
                    Log.e("Fetch Product"," API call failed with status code $statusCode and error message $errorMessage")
                    onResult(null)
                }
                override fun onResponse(call: Call<GetProductByIdResponse>, response: Response<GetProductByIdResponse>) {
                    Log.i("Fetch  Product","Products fetched successfully")
                    val response = response.body()
                    onResult(response)
                }
            }
        )
    }
}