package com.frood.app.presentation.util.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.frood.app.R
import com.frood.app.databinding.ItemListIndicatorBinding
import com.frood.app.domain.model.Indicator
import com.frood.app.presentation.util.comparator.ModelComparator
import com.frood.app.presentation.util.extension.colorStateListFromAttrOf

class AdapterIndicator:
    ListAdapter<Indicator, AdapterIndicator.ViewHolder>(ModelComparator<Indicator>()) {

    class ViewHolder(val binding: ItemListIndicatorBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_list_indicator,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.binding.root.context
        val isActive = getItem(position).isActive

        holder.binding.imageIndicator.backgroundTintList = context.colorStateListFromAttrOf(
            if (isActive) R.attr.colorIndicatorOn
            else R.attr.colorIndicatorOff
        )
    }
}