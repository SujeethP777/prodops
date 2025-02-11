package com.example.prodops.productList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.responseModels.Product
import com.example.prodops.R

@Composable
fun ProductListScreen(viewModel: ProductListViewModel = viewModel()) {
    LaunchedEffect(true) {
        viewModel.productList()
    }

    val products by viewModel.products.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(products, key = { it.productCode ?: it.hashCode().toString() }) { product ->
                ProductItem(
                    product = product,
                    onDelete = {
                        viewModel.removeProduct(product)
                    }
                )
            }
        }

        Button(
            onClick = { viewModel.saveProducts() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF6200EA))
        ) {
            Text("Save", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}



@Composable
fun ProductItem(
    product: Product,
    onDelete: () -> Unit
) {
    var rate by rememberSaveable { mutableStateOf(0) }
    var quantity by rememberSaveable { mutableStateOf(1) }
    val total = remember(rate, quantity) { rate * quantity }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = product.productName ?: "",
            modifier = Modifier.weight(2f),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        TextField(
            value = rate.toString(),
            onValueChange = { newValue -> rate = newValue.toIntOrNull() ?: 0 },
            modifier = Modifier.width(50.dp)
        )

        IconButton(
            onClick = { if (quantity > 1) quantity-- },
            modifier = Modifier.size(30.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.remove_box), contentDescription = "-")
        }

        TextField(
            value = quantity.toString(),
            onValueChange = { newValue -> quantity = newValue.toIntOrNull() ?: 1 },
            modifier = Modifier.width(50.dp)
        )

        IconButton(
            onClick = { quantity++ },
            modifier = Modifier.size(30.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.add_box), contentDescription = "+")
        }

        Text(
            text = total.toString(),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )

        Button(
            onClick = onDelete,
            colors = ButtonDefaults.buttonColors(Color(0xFF6200EA)),
            shape = RectangleShape
        ) {
            Text("DEL", color = Color.White, fontSize = 8.sp)
        }
    }

    HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
}




@Preview
@Composable
fun ProductListScreenPreview() {
    ProductListScreen()
}