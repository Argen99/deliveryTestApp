package com.example.deliverytestapp.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deliverytestapp.databinding.ItemPizzaBinding
import com.example.deliverytestapp.utils.loadImage
import com.example.domain.model.PizzaModel

class PizzaAdapter(
    private var pizzasList: List<PizzaModel>
) : RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(list: List<PizzaModel>) {
        pizzasList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        return PizzaViewHolder(
            ItemPizzaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount() = pizzasList.size

    inner class PizzaViewHolder(private val binding: ItemPizzaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(position: Int) {
            val pizza = pizzasList[position]

            binding.apply {
                ivPizza.loadImage(pizza.img)
                tvTitle.text = pizza.name
                tvDescription.text = pizza.description
                tvPrice.text = "от ${pizza.price} р"
            }
        }
    }
}