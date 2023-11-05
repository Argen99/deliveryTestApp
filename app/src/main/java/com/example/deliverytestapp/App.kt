package com.example.deliverytestapp

import android.app.Application
import com.example.data.koin.dataModule
import com.example.deliverytestapp.koin.appModule
import com.example.domain.koin.domainModule
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(appModule, dataModule, domainModule))
        }
    }
}