package com.example.data.repository

import com.example.common.ResponseWrapper
import com.example.data.datasource.ProductDatasource
import com.example.domain.repository.ProductRepository
import com.example.domain.responseModels.Product
import com.example.domain.responseModels.ProductResponseContainer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImpl
@Inject
constructor(
    private val datasource: ProductDatasource
) : ProductRepository {

    override suspend fun productList(): Flow<ResponseWrapper<List<Product>>> =
        datasource.productList()

    override suspend fun updateProductDetails(products: List<Product>): Flow<ResponseWrapper<ProductResponseContainer>> {
        return datasource.updateProductDetails(products)
    }
}
