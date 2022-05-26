package com.frood.app.presentation.util.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.frood.app.R
import com.frood.app.databinding.ItemListInformationBinding
import com.frood.app.domain.model.Information
import com.frood.app.presentation.util.comparator.SimpleComparator
import javax.inject.Inject

class InformationPagerAdapter:
    ListAdapter<Information, InformationPagerAdapter.ViewHolder>(SimpleComparator<Information>()) {

    class ViewHolder(val binding: ItemListInformationBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_list_information,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        Glide.with(holder.binding.root)
            .load(item.imageRes)
            .into(holder.binding.imageContent)
    }
}