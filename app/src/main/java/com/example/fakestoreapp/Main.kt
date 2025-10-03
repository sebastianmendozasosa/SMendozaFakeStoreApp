package com.example.fakestoreapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main(){
    //hilo
    println("Incio en hilo: ${Thread.currentThread().name}")
    cWithContext()
    println("Fin en hilo: ${Thread.currentThread().name}")
    // Corrutinas
}

fun MyGlobal(){
    GlobalScope.launch {
        SaludoAsincrono()
    }
}

//suspend fun

suspend fun SaludoAsincrono(){
    println("Hola")
    delay(2000)
    println("Termine de saludarte")
}
//Dispatchers
//Edificio 1 App - UI
//Edificio 2
fun cAsync(){
    runBlocking {
        val result = async(Dispatchers.IO) {
            println("Consultando api")
            delay(5000)
            println("Termine de consultar api")
            "Datos"
        }
        println("El resultado es: ${result.await()}")
    }
}

fun cWithContext(){
    runBlocking {
        val result = withContext(Dispatchers.IO){
            println("WithContext en hilo: ${Thread.currentThread().name}")
            println("Consultando api")
            delay(5000)
            println("Termine de consultar api")
            "{ age: 17}"
        }
        println("El resultado es: ${result}")
    }
}