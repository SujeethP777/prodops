package com.example.domain.usecase

import com.example.common.ResponseWrapper
import com.example.domain.repository.ProductRepository
import com.example.domain.responseModels.Product
import com.example.domain.responseModels.ProductResponseContainer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SubmitProductUseCase {
    suspend fun updateProductDetails(products: List<Product>): Flow<ResponseWrapper<ProductResponseContainer>>
}

class SubmitProductUseCaseImpl
@Inject
constructor(
    private val repository: ProductRepository
) : SubmitProductUseCase {

    override suspend fun updateProductDetails(products: List<Product>): Flow<ResponseWrapper<ProductResponseContainer>> {
        return repository.updateProductDetails(products)
    }
}

