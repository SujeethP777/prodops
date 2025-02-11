package com.example.data.datasource

import android.util.Log
import com.example.common.FailureResponseWrapper
import com.example.common.ResponseWrapper
import com.example.common.SuccessResponseWrapper
import com.example.data.network.NetworkClient
import com.example.data.network.NetworkService
import com.example.domain.responseModels.Product
import com.example.domain.responseModels.ProductResponseContainer
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface ProductDatasource {
    suspend fun productList(): Flow<ResponseWrapper<List<Product>>>

    suspend fun updateProductDetails(products: List<Product>): Flow<ResponseWrapper<ProductResponseContainer>>
}


class ProductDatasourceImpl
@Inject
constructor(
    private val networkClient: NetworkClient
) : ProductDatasource {

    override suspend fun productList(): Flow<ResponseWrapper<List<Product>>> {
        val api = networkClient.makeService(NetworkService::class.java)
        return flow {
            val responseWrapper =
                networkClient.execute {
                    api.getProducts()
                }

            // Handle the success and failure response.
            when (responseWrapper) {
                is SuccessResponseWrapper -> emit(SuccessResponseWrapper(responseWrapper.data))
                is FailureResponseWrapper -> emit(FailureResponseWrapper(responseWrapper.throwable))
                else -> {}
            }
        }
    }

    override suspend fun updateProductDetails(products: List<Product>): Flow<ResponseWrapper<ProductResponseContainer>> {
        val api = networkClient.makeService(NetworkService::class.java)

        return flow {
            val jsonData = Gson().toJson(mapOf("data" to products)) // Convert list to JSON
            val responseWrapper = networkClient.execute {
                api.updateProductDetails(jsonData) // Send JSON as String
            }

            when (responseWrapper) {
                is SuccessResponseWrapper -> emit(SuccessResponseWrapper(responseWrapper.data))
                is FailureResponseWrapper -> emit(FailureResponseWrapper(responseWrapper.throwable))
            }
        }
    }
}