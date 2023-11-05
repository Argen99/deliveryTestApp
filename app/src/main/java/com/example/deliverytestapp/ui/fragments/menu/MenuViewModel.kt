package com.example.deliverytestapp.ui.fragments.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliverytestapp.utils.UIState
import com.example.domain.Either
import com.example.domain.model.PizzaModel
import com.example.domain.use_cases.GetPizzasUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MenuViewModel(
    private val getPizzasUseCase: GetPizzasUseCase
) : ViewModel() {

    private val _pizzasState = MutableStateFlow<UIState<List<PizzaModel>>>(UIState.Idle())
    val pizzasState = _pizzasState.asStateFlow()

    init {
        getPizzas()
    }

    private fun getPizzas() {
        viewModelScope.launch(Dispatchers.IO) {
            _pizzasState.value = UIState.Loading()
            getPizzasUseCase().collect {
                when (it) {
                    is Either.Left -> _pizzasState.value = UIState.Error(it.value)
                    is Either.Right -> _pizzasState.value = UIState.Success(it.value)
                    else -> {}
                }
            }
        }
    }
}