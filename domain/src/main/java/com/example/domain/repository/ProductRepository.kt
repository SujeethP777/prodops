package com.example.domain.repository

import com.example.common.ResponseWrapper
import com.example.domain.responseModels.Product
import com.example.domain.responseModels.ProductResponseContainer
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun productList(): Flow<ResponseWrapper<List<Product>>>

    suspend fun updateProductDetails(products: List<Product>): Flow<ResponseWrapper<ProductResponseContainer>>
}
