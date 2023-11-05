package com.example.data.koin

import com.example.data.remote.api_service.PizzaApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.domain.repository.PizzaRepository
import com.example.data.remote.reposotory.PizzaRepositoryImpl
import org.koin.core.module.dsl.bind
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://pizza-and-desserts.p.rapidapi.com"

val dataModule = module {
    factory { provideOkHttpClient() }
    factory { provideForecastApi(get()) }
    single { provideRetrofit(get()) }
    singleOf(::PizzaRepositoryImpl) {
        bind<PizzaRepository>()
    }
}
fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient().newBuilder()
        .addInterceptor(interceptor)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()
}

fun provideForecastApi(retrofit: Retrofit): PizzaApiService {
    return retrofit.create(PizzaApiService::class.java)
}