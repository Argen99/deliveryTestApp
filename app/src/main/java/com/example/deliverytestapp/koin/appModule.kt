package com.example.deliverytestapp.koin

import org.koin.androidx.viewmodel.dsl.viewModelOf
import com.example.deliverytestapp.ui.fragments.menu.MenuViewModel
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::MenuViewModel)
}