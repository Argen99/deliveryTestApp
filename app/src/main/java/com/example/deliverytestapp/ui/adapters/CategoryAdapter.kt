package com.example.deliverytestapp.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deliverytestapp.databinding.ItemCategoryBinding

class CategoryAdapter(
    private var categories: List<RadioButtonInfo>
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var isNewRadioButtonChecked = false
    private var lastCheckedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount() = categories.size

    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int) {
            val category = categories[position]
            binding.apply {
                rbCategory.text = category.name
                if (isNewRadioButtonChecked) {
                    rbCategory.isChecked = category.isChecked
                } else {
                    if (adapterPosition == 0) {
                        rbCategory.isChecked = true
                        lastCheckedPosition = 0
                    }
                }
            }
        }

        init {
            binding.root.setOnClickListener {
                handleRadioButtonChecked(adapterPosition)
            }
        }

        @SuppressLint("NotifyDataSetChanged")
        private fun handleRadioButtonChecked(adapterPosition: Int) {
            isNewRadioButtonChecked = true
            categories[lastCheckedPosition].isChecked = false
            categories[adapterPosition].isChecked = true
            lastCheckedPosition = adapterPosition
            notifyDataSetChanged()
        }
    }
}

data class RadioButtonInfo(
    val name: String,
    var isChecked: Boolean = false
)