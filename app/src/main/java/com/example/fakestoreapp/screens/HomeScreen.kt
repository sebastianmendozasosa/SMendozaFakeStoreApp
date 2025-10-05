package com.example.fakestoreapp.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fakestoreapp.components.ProductCard
import com.example.fakestoreapp.models.Product
import com.example.fakestoreapp.services.ProductService
import com.example.fakestoreapp.ui.theme.FakeStoreAppTheme
import com.example.fakestoreapp.ui.theme.ProductDetailScreenRoute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable

fun HomeScreen(
    navController: NavController
){
    /*
    * */
    var products by remember {
        mutableStateOf(listOf<Product>())
    }
    var loading by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(true) {
        try {
            //1. Crear una instancia de retrofit create
            val retrofit = Retrofit
                .Builder()
                .baseUrl("https://fakestoreapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(ProductService::class.java)
            val result = async(Dispatchers.IO) {
                service.getAllProducts()
            }
            Log.i("HomeScreen","${result.await()}")
            products = result.await()
            loading = false

        }
        catch (e: Exception){
            loading = false
            Log.e("HomeScreen",e.toString())
        }
    }

    if(loading){
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 8.dp),
                content = {
                    items(products) { product ->
                        ProductCard(
                            product = product,
                            onClick = {
                                navController.navigate(ProductDetailScreenRoute(product.id))
                            }
                        )
                    }
                }
            )
    }



    /*
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(products){ product ->
            ProductCard(
                product = product,
                onClick = {
                    navController.navigate(ProductDetailScreenRoute(product.id))
                }
            )

        }
    } */
}

@Preview
@Composable
fun HomeScreenPreview(){
    FakeStoreAppTheme {
        HomeScreen(rememberNavController())
    }
}