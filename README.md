# Product Management App

## Description
This is an Android application built using Jetpack Compose that allows users to manage product details efficiently. The app fetches product data from an API, displays it in a list, and enables users to edit quantities, calculate total values dynamically, and save the updated product details.

## Features

### Product List Screen
- Displays a list of products fetched from the API.
- Each product row includes:
  - **Product Name**: Displays the name of the product.
  - **Quantity Input**: Allows users to enter quantity manually.
  - **Increase/Decrease Buttons**: Users can adjust the quantity.
  - **Rate Input**: Editable rate field.
  - **Total Calculation**: Updates dynamically based on quantity and rate.
  - **Delete Button**: Allows users to remove a product from the list.
  
### Save Product Feature
- Saves or updates the modified product list.
- Sends the updated data to the API via a POST request.
- Displays a toast notification based on the API response:
  - **Success**: "Products are saved successfully."
  - **Failure**: Displays the error message received from the API.

## Tech Stack
- **Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **UI Framework**: Jetpack Compose
- **Dependency Injection**: Hilt
- **Networking**: Retrofit + OkHttp
- **Coroutines & Flow**: For asynchronous operations
- **State Management**: StateFlow

## Assumptions
- The app retrieves product data from a remote API.
- Products have a unique `product_code`.
- The total value is calculated as `Quantity × Rate`.
- API responses are properly handled and mapped to UI elements.
