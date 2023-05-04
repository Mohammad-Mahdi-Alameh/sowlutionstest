package com.example.sowlutions.api.product

import com.example.sowlutions.api.PublicHeaders
import com.example.sowlutions.models.data.Product
import com.example.sowlutions.models.response.GetAllProductsResponse
import com.example.sowlutions.models.response.GetProductByIdResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Path

interface RestApiRoutes {

    @GET("whats_new?limit=100000")
    fun getAllProducts(
        @HeaderMap getPublicHeaders: PublicHeaders
    ): Call<GetAllProductsResponse>

    @GET("{product_id}")
    fun getProductById(
        @HeaderMap getPublicHeaders: PublicHeaders,
        @Path ("product_id") productId : Int,
    ): Call <GetProductByIdResponse>

}