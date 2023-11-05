package com.example.domain.use_cases

import com.example.domain.repository.PizzaRepository

class GetPizzasUseCase(
    private val repository: PizzaRepository
) {
    operator fun invoke() = repository.getPizzas()
}