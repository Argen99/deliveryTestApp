package com.example.data.remote.api_service

import com.example.data.remote.model.PizzaDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface PizzaApiService {

    @GET(PIZZA_ENDPOINT)
    suspend fun getPizzas(
        @Header("X-RapidAPI-Key") key: String = KEY
    ): List<PizzaDto>

    companion object {
        const val PIZZA_ENDPOINT = "/pizzas"
        const val KEY = "990f2e9d00msh29cfbbc0ddfe386p194291jsna4cd6a935797"
    }
}