package com.example.fakestoreapp.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.example.fakestoreapp.models.Product
import com.example.fakestoreapp.models.Rating
import com.example.fakestoreapp.services.ProductService
import com.example.fakestoreapp.ui.theme.HomeScreenRoute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Composable
fun ProductDetailScreen(id: Int, navController: NavController) {

    var product by remember {
        mutableStateOf<Product?>(null)
    }

    LaunchedEffect(true) {
        try {
            val retrofit = Retrofit
                .Builder()
                .baseUrl("https://fakestoreapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(ProductService::class.java)
            val result = withContext(Dispatchers.IO) {
                service.getProductById(id)
            }
            product = result
            Log.i("ProductDetailScreen", product.toString())
        } catch (e: Exception) {
            // Manejo de error
            Log.e("ProductDetailScreen", e.toString())
        }
    }
    // Aquí iría la implementación de la pantalla de detalle del producto

    /*
    Box(
        modifier = Modifier .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(id.toString())
    }
    */

    product?.let { prod ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = prod.image,
                contentDescription = prod.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
            ) {
                Column (
                    modifier = Modifier
                        .padding(16.dp)
                ){
                    Text(
                        text = prod.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = prod.description,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text= "Price: $${prod.price}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()//No hay logica por ahora de "agregar a carrito" es puro diseño
                ) {
                    Text("Add to Cart")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {navController.navigate(HomeScreenRoute) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Volver al inicio")
                }
            }
        }
    } ?: Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Loading...")
    }
}

@Preview
@Composable
fun ProductDetailScreenPreview() {
    ProductDetailScreen(1, rememberNavController())
}

