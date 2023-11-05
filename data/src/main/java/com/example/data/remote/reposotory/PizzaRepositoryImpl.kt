package com.example.data.remote.reposotory

import com.example.data.remote.api_service.PizzaApiService
import com.example.domain.Either
import com.example.domain.model.PizzaModel
import com.example.domain.repository.PizzaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PizzaRepositoryImpl(
    private val apiService: PizzaApiService
): PizzaRepository {

    override fun getPizzas(): Flow<Either<String, List<PizzaModel>>> = makeNetworkRequest {
        apiService.getPizzas().map { it.toDomain() }
    }

    private fun <T> makeNetworkRequest(
        request: suspend () -> T
    ) =
        flow<Either<String, T>> {
            request().also {
                emit(Either.Right(value = it))
            }
        }.flowOn(Dispatchers.IO).catch { exception ->
            emit(Either.Left(value = exception.localizedMessage ?: "Error Occurred!"))
        }
}