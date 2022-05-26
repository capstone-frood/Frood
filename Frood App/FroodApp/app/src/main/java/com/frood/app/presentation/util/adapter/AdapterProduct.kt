package com.frood.app.presentation.util.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.frood.app.R
import com.frood.app.databinding.ItemListProductBinding
import com.frood.app.domain.model.Product
import com.frood.app.presentation.util.comparator.ModelComparator
import javax.inject.Inject

class AdapterProduct @Inject constructor() : ListAdapter<Product, AdapterProduct.ViewHolder>(ModelComparator<Product>()) {

    class ViewHolder(val binding: ItemListProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_list_product,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.product = item
        Glide.with(holder.binding.root)
            .load(item.imageSrc)
            .into(holder.binding.imageUrl)
    }
}