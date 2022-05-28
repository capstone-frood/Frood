package com.frood.app.presentation.ui.home.fruit

import com.frood.app.domain.model.Product

interface FruitContract {

    interface View {
        fun fetchFruitList(fruitList: List<Product>)
    }
}