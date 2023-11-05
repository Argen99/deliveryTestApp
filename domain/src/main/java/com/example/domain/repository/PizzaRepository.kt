package com.example.domain.repository

import com.example.domain.Either
import com.example.domain.model.PizzaModel
import kotlinx.coroutines.flow.Flow

interface PizzaRepository {

    fun getPizzas(): Flow<Either<String,List<PizzaModel>>>
}