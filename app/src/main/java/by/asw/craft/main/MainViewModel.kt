package by.asw.craft.main

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
        options.add(DataModel("Хлеб и выпечка", R.drawable.bread, "#fca43c", 1))
        options.add(DataModel("Молочные продукты", R.drawable.milk, "#3a7edb",2 ))
        options.add(DataModel("Рыба", R.drawable.fish, "#21bfcd", 3))
        options.add(DataModel("Мясо", R.drawable.meat, "#f53d3d", 4))
        options.add(DataModel("Мед", R.drawable.honey, "#fdf750", 5))
        options.add(DataModel("Овощи и фрукты", R.drawable.vegetables, "#55e76a", 6))
        return options
    }

    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
    }

    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }
}