package com.frood.app.presentation.ui.home.meat

import com.frood.app.domain.model.Product

interface MeatContract {

    interface View {
        fun fetchMeatList(meatList: List<Product>)
    }
}