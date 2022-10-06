package by.asw.craft.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.asw.craft.DataModel
import by.asw.craft.R

class MainViewModel : ViewModel() {
    private val _quantity = MutableLiveData<Int>(0)
    val quantity: LiveData<Int> = _quantity

    private val _flavor = MutableLiveData<String>("")
    val flavor: LiveData<String> = _flavor

    private val _buttondate: List<DataModel> = getButtonData()
    //private lateinit var _buttondate: MutableList<DataModel>
    val date: List<DataModel> = _buttondate

    private val _price = MutableLiveData<Double>(0.0)
    val price: LiveData<Double> = _price

    fun getButtonData() :List<DataModel> {
        val options = mutableListOf<DataModel>()
        options.add(DataModel("Выпечка", R.drawable.battle, "#09A9FF"))
        options.add(DataModel("Молочные продукты", R.drawable.beer, "#3E51B1"))
        options.add(DataModel("Мясо и мясные изделия", R.drawable.ferrari, "#673BB7"))
        options.add(DataModel("Item 4", R.drawable.jetpack_joyride, "#4BAA50"))
        options.add(DataModel("Item 5", R.drawable.three_d, "#F94336"))
        return options
    }

    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
    }

    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }
}