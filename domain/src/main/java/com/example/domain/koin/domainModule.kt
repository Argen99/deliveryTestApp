package com.example.domain.koin

import com.example.domain.use_cases.GetPizzasUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetPizzasUseCase)
}