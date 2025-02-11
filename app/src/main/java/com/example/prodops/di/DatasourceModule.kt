package com.example.prodops.di

import com.example.data.datasource.ProductDatasource
import com.example.data.datasource.ProductDatasourceImpl
import com.example.data.network.NetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatasourceModule {

    @Provides
    @Singleton
    fun provideProductsListDataSource(networkService: NetworkClient): ProductDatasource =
        ProductDatasourceImpl(networkService)
}