package com.example.data.network

import com.example.domain.responseModels.Product
import com.example.domain.responseModels.ProductResponseContainer
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface NetworkService {

    @GET("server/native_Db_V13.php?axn=get/taskproducts&divisionCode=258")
    suspend fun getProducts(): Response<List<Product>>

    @FormUrlEncoded
    @POST("server/native_Db_V13.php?axn=save/taskproddets&divisionCode=258")
    suspend fun updateProductDetails(
        @Field("data") data: String
    ): Response<ProductResponseContainer>


}