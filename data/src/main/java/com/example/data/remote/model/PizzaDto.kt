package com.example.data.remote.model

import com.example.data.utils.DataMapper
import com.example.domain.model.PizzaModel

data class PizzaDto(
    val id: Int,
    val name: String,
    val price: Int,
    val description: String,
    val img: String,
) : DataMapper<PizzaModel> {

    override fun toDomain() = PizzaModel(
        id = id,
        name = name,
        price = price,
        description = description,
        img = img
    )
}