package com.frood.app.presentation.ui.information

import androidx.lifecycle.LiveData
import com.frood.app.domain.model.Indicator
import com.frood.app.domain.model.Information

interface InformationContract {

    interface View {
        fun showInformationList(informations: List<Information>)
        fun showIndicatorList(indicators: List<Indicator>)

        fun openHomePage()
        fun openScanPage()
    }

    interface ViewModel {
        val informationListObservable: LiveData<List<Information>>
        val indicatorListObservable: LiveData<List<Indicator>>

        fun setInformationList()
        fun setInformationPosition(position: Int)
    }

}