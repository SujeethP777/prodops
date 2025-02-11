package com.example.prodops.productList

import android.app.Application
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.FailureResponseWrapper
import com.example.common.SuccessResponseWrapper
import com.example.domain.responseModels.Product
import com.example.domain.usecase.ProductUseCase
import com.example.domain.usecase.SubmitProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.logging.Handler
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val submitProductUseCase: SubmitProductUseCase,
    private val application: Application // Needed for Toast
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    fun productList() {
        viewModelScope.launch {
            productUseCase.fetchProduct()
                .collect {
                    when (it) {
                        is SuccessResponseWrapper -> _products.value = it.data
                        is FailureResponseWrapper -> Log.d("ProductListViewModel", it.throwable.toString())
                    }
                }
        }
    }

    fun removeProduct(product: Product) {
        _products.value = _products.value.filter { it.productCode != product.productCode }
    }

    fun saveProducts() {
        viewModelScope.launch {
            val productList = _products.value
            submitProductUseCase.updateProductDetails(productList)
                .collect { response ->
                    when (response) {
                        is SuccessResponseWrapper -> {
                            if (response.data.success) {
                                showToast("Products saved successfully")
                            } else {
                                showToast("Failed to save products. Please try again.")
                            }
                        }
                        is FailureResponseWrapper -> {
                            showToast("Save Failed: ${response.throwable.message}")
                        }
                    }
                }
        }
    }

    private fun showToast(message: String) {
        viewModelScope.launch(Dispatchers.Main) {
            Toast.makeText(application, message, Toast.LENGTH_SHORT).show()
        }
    }
}
