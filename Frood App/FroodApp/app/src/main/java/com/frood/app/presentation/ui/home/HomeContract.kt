package com.frood.app.presentation.ui.home

import androidx.lifecycle.LiveData
import com.frood.app.domain.model.Product

interface HomeContract {

    interface View {
        fun initViewPagerFragment()

        fun openScanPage()
        fun openInformationPage()
    }

    interface ViewModel {
        val meatListObservable: LiveData<List<Product>>
        val fruitListObservable: LiveData<List<Product>>

        fun loadData()
    }

}