package com.example.fakestoreapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.fakestoreapp.models.Product
import com.example.fakestoreapp.models.Rating
import com.example.fakestoreapp.ui.theme.CardBackground
import com.example.fakestoreapp.ui.theme.FakeStoreAppTheme

@Composable
fun ProductCard(
    product: Product,
    onClick : () -> Unit
) {

/*
    // Implementación del ProductCard
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(100.dp)
            .clip(CircleShape)
            .background(Color.Green)
            .padding(10.dp)
            .clickable{
              onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = product.image,
            contentDescription = product.title,
            modifier = Modifier .size(50.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentScale = ContentScale.Crop

        )

        Column(
            modifier = Modifier
                .padding(10.dp)
                .weight(1f)
        ) {
            Text( product.title)
            Text(product.category)
        }
        Text( "$${product.price}" )
    }

    */

    Column(
        modifier = Modifier
            .padding(8.dp)
            .width(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(CardBackground)
            .clickable{
                onClick()
            }
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = product.image,
            contentDescription = product.title,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        // nombre
        Text(
            text = product.title,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            maxLines = 1
        )

        //categoria
        Text(
            text = product.category,
            fontSize = 12.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(8.dp))

        //precio
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .padding(horizontal = 10.dp, vertical = 4.dp)
        ){
            Text(
                text = "$${product.price}",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = Color.Black
            )

        }

    }
}


@Preview
@Composable
fun ProductCardPreview() {
    val testProduct = Product(
        id = 1,
        title = "Camiseta de prueba",
        price = 19.99,
        description = "Camiseta cómoda y de alta calidad.",
        category = "Ropa",
        image = "https://ejemplo.com/camiseta.png",
        rating = Rating(rate = 4.5, count = 120)
    )
    FakeStoreAppTheme {
        ProductCard(
            product = testProduct,
            onClick = { }
        )
    }
}