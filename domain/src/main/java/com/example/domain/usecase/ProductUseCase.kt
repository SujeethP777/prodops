package com.example.domain.usecase

import com.example.common.ResponseWrapper
import com.example.domain.repository.ProductRepository
import com.example.domain.responseModels.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ProductUseCase{
    suspend fun fetchProduct(): Flow<ResponseWrapper<List<Product>>>
}

class ProductUseCaseImpl
@Inject
constructor(
    private val repository: ProductRepository
): ProductUseCase {
    override suspend fun fetchProduct(): Flow<ResponseWrapper<List<Product>>> {
        return repository.productList()
    }
}