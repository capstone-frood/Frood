package com.frood.app.presentation.ui.information

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frood.app.R
import com.frood.app.domain.model.Indicator
import com.frood.app.domain.model.Information
import com.frood.app.presentation.util.extension.freshInsert

class InformationViewModel: ViewModel(), InformationContract.ViewModel {

    private var informationPosition: Int = 0
    private val informationPositionObservable = MutableLiveData<Int>()
    private val informationList = arrayListOf<Information>()

    private val _informationListObservable = MutableLiveData<List<Information>>()
    override val informationListObservable: LiveData<List<Information>>
        get() = _informationListObservable

    private val _indicatorListObservable = MediatorLiveData<List<Indicator>>()
    override val indicatorListObservable: LiveData<List<Indicator>>
        get() = _indicatorListObservable

    init {
        informationPositionObservable.value = 0
        _informationListObservable.value = emptyList()
        _indicatorListObservable.apply {
            value = emptyList()
            addSource(informationPositionObservable) { data ->
                if (data != null) informationPosition = data
                postValue(getInformationIndicatorList())
            }
            addSource(informationListObservable) { data ->
                if (data != null) informationList.freshInsert(data)
                postValue(getInformationIndicatorList())
            }
        }
    }

    override fun setInformationList() {
        val listInformation = listOf(
            Information(id = 1, imageRes = R.drawable.img_information_1),
            Information(id = 2, imageRes = R.drawable.img_information_2),
            Information(id = 3, imageRes = R.drawable.img_information_3),
            Information(id = 4, imageRes = R.drawable.img_information_4)
        )
        _informationListObservable.value = listInformation
    }

    override fun setInformationPosition(position: Int) {
        informationPositionObservable.value = position
    }

    private fun getInformationIndicatorList(): List<Indicator> {
        return informationList.mapIndexed { index, _ ->
            Indicator(index = index, isActive = index == informationPosition)
        }
    }
}