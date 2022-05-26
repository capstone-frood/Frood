package com.frood.app.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frood.app.R
import com.frood.app.domain.model.Product
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel(), HomeContract.ViewModel {

    private val _meatListObservable = MutableLiveData<List<Product>>()
    override val meatListObservable: LiveData<List<Product>>
        get() = _meatListObservable

    private val _fruitListObservable = MutableLiveData<List<Product>>()
    override val fruitListObservable: LiveData<List<Product>>
        get() = _fruitListObservable

    override fun loadData() {
        viewModelScope.launch {
//            Mengisi list daging (Editable)
            val meatList = listOf(
                Product(id = 1, name = "Beef", imageSrc = R.drawable.img_beef, roomTemp = 2, FridgeTemp = 12),
                Product(id = 2, name = "Chicken", imageSrc = R.drawable.img_chicken, roomTemp = 3, FridgeTemp = 10),
                Product(id = 3, name = "Fish", imageSrc = R.drawable.img_fish, roomTemp = 1, FridgeTemp = 4)
            )
//            Mengisi list buah dan sayur (Editable)
            val fruitList = listOf(
                Product(id = 1, name = "Apple", imageSrc = R.drawable.img_apple, roomTemp = 5, FridgeTemp = 12),
                Product(id = 2, name = "Banana", imageSrc = R.drawable.img_banana, roomTemp = 7, FridgeTemp = 12),
                Product(id = 3, name = "Tomato", imageSrc = R.drawable.img_tomato, roomTemp = 9, FridgeTemp = 20),
                Product(id = 4, name = "Eggplant", imageSrc = R.drawable.img_eggplant, roomTemp = 7, FridgeTemp = 10),
                Product(id = 5, name = "Cabbage", imageSrc = R.drawable.img_cabbage, roomTemp = 10, FridgeTemp = 25),
                Product(id = 6, name = "Orange", imageSrc = R.drawable.img_orange, roomTemp = 20, FridgeTemp = 30),
                Product(id = 7, name = "Strawberry", imageSrc = R.drawable.img_strawberry, roomTemp = 7, FridgeTemp = 10)
            )
//            Passing ke View
            _meatListObservable.postValue(meatList.sortedBy { a ->
                a.name
            })
            _fruitListObservable.postValue(fruitList.sortedBy { a ->
                a.name
            })
        }
    }
}