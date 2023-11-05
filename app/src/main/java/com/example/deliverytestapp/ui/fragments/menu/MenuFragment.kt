package com.example.deliverytestapp.ui.fragments.menu

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.deliverytestapp.R
import com.example.deliverytestapp.databinding.FragmentMenuBinding
import com.example.deliverytestapp.ui.adapters.BannerAdapter
import com.example.deliverytestapp.ui.adapters.CategoryAdapter
import com.example.deliverytestapp.ui.adapters.PizzaAdapter
import com.example.deliverytestapp.ui.adapters.RadioButtonInfo
import com.example.deliverytestapp.utils.UIState
import com.example.domain.model.PizzaModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private val binding by viewBinding(FragmentMenuBinding::bind)
    private val viewModel by viewModel<MenuViewModel>()
    private var categories = arrayListOf<RadioButtonInfo>()
    private val categoriesAdapter: CategoryAdapter by lazy {
        CategoryAdapter(categories)
    }
    private val bannerList = arrayListOf<Int>()
    private val bannerAdapter: BannerAdapter by lazy {
        BannerAdapter(bannerList)
    }
    private val pizzasList = arrayListOf<PizzaModel>()
    private val pizzaAdapter: PizzaAdapter by lazy {
        PizzaAdapter(pizzasList)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        observers()
    }

    private fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pizzasState.collect { result ->
                when (result) {
                    is UIState.Loading -> {
                        binding.progressBar.isVisible = true
                    }

                    is UIState.Error -> {
                        binding.progressBar.isGone = true
                        Toast.makeText(requireContext(), result.error, Toast.LENGTH_SHORT).show()
                    }

                    is UIState.Success -> {
                        binding.progressBar.isGone = true
                        pizzaAdapter.submitData(result.data)
                    }

                    else -> binding.progressBar.isGone = true
                }
            }
        }
    }

    private fun initialize() {
        val list = resources.getStringArray(R.array.categories).toList()
        list.forEach {
            categories.add(RadioButtonInfo(it))
        }
        repeat(6) {
            bannerList.add(R.drawable.banner)
        }

        binding.rvCategories.apply {
            adapter = categoriesAdapter
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL, false
            )
        }

        binding.rvBanner.apply {
            adapter = bannerAdapter
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL, false
            )
        }

        binding.rvPizza.apply {
            adapter = pizzaAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }
}