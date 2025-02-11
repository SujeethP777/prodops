package com.example.prodops.di

import com.example.data.datasource.ProductDatasource
import com.example.data.repository.ProductRepositoryImpl
import com.example.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductsListRepository(datasource: ProductDatasource): ProductRepository =
        ProductRepositoryImpl(datasource)
}