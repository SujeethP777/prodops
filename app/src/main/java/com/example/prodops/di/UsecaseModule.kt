package com.example.prodops.di

import com.example.domain.repository.ProductRepository
import com.example.domain.usecase.ProductUseCase
import com.example.domain.usecase.ProductUseCaseImpl
import com.example.domain.usecase.SubmitProductUseCase
import com.example.domain.usecase.SubmitProductUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideProductsListUseCase(repository: ProductRepository): ProductUseCase =
        ProductUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideSaveProductsUseCase(repository: ProductRepository): SubmitProductUseCase =
        SubmitProductUseCaseImpl(repository)
}