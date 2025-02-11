package com.example.domain.responseModels

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("product_code")
    val productCode: String?,
    @SerializedName("product_name")
    val productName: String?,
    @SerializedName("product_unit")
    val productUnit: String?,
    @SerializedName("convQty")
    val convQty: String?
)