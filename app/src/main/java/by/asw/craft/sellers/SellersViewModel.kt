package by.asw.craft.sellers

import android.app.Application
import androidx.lifecycle.*
import by.asw.craft.DataModel
import by.asw.craft.network.CatalogItem
import by.asw.craft.network.CommonCraftApiFilter
import by.asw.craft.network.CraftTransportApi.retrofitService
import kotlinx.coroutines.launch
import java.lang.Exception

enum class ImageLoadedStatus { LOADING, ERROR, DONE }

class SellersViewModel(catalogueType: DataModel, app: Application) : AndroidViewModel(app) {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<ImageLoadedStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<ImageLoadedStatus>
        get() = _status

    // Internally, we use a MutableLiveData, because we will be updating the List of MarsProperty
    // with new values
    private val _properties = MutableLiveData<List<CatalogItem>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val properties: LiveData<List<CatalogItem>>
        get() = _properties

    // Internally, we use a MutableLiveData to handle navigation to the selected property
    private val _navigateToSelectedProperty = MutableLiveData<CatalogItem>()

    // The external immutable LiveData for the navigation property
    val navigateToSelectedProperty: LiveData<CatalogItem>
        get() = _navigateToSelectedProperty


    var currentCatalog:DataModel
    /**
     * Call getCatalogueDataByParams() on init so we can display status immediately.
     */
    init {
        currentCatalog = catalogueType
        getCatalogueDataByParams(CommonCraftApiFilter.SHOW_ALL)
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getCatalogueDataByParams(filter: CommonCraftApiFilter) {
//        _response.value = MarsApi.retrofitService.getProperties().enqueue(
//            object : Callback<List<MarsProperty>> {
//                override fun onResponse(call: Call<List<MarsProperty>>, response: Response<List<MarsProperty>>) {
//                    _response.value = "Success: ${response.body()?.size} Mars properties retrieved"
//                }
//
//                override fun onFailure(call: Call<List<MarsProperty>>, t: Throwable) {
//                    _response.value = "Failure: " + t.message
//                }
//            })
//            .toString()

        viewModelScope.launch {
            _status.value = ImageLoadedStatus.LOADING
            try {
                _properties.value = retrofitService.getProperties(filter.value, currentCatalog.catalogueType)
                _status.value = ImageLoadedStatus.DONE
            } catch (e: Exception) {
                _status.value = ImageLoadedStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }
    fun displayPropertyDetails(catalogProperty: CatalogItem) {
        _navigateToSelectedProperty.value = catalogProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }
    fun updateFilter(filter: CommonCraftApiFilter) {
        getCatalogueDataByParams(filter)
    }

/*
    private val _selectedProperty = MutableLiveData<DataModel>()


    // The external LiveData for the SelectedProperty
    val selectedProperty: LiveData<DataModel>
        get() = _selectedProperty

    // Initialize the _selectedProperty MutableLiveData
    init {
        _selectedProperty.value = catalogueType
    }

    // The displayPropertyPrice formatted Transformation Map LiveData, which displays the sale
    // or rental price.
    val displayPropertyPrice = Transformations.map(selectedProperty) {
//        app.applicationContext.getString(
//            when (it.isRental) {
//                true -> R.string.display_price_monthly_rental
//                false -> R.string.display_price
//            }, it.price)
    }

    // The displayPropertyType formatted Transformation Map LiveData, which displays the
    // "For Rent/Sale" String
    val displayPropertyType = Transformations.map(selectedProperty) {
//        app.applicationContext.getString(R.string.display_type,
//            app.applicationContext.getString(
//                when(it.isRental) {
//                    true -> R.string.type_rent
//                    false -> R.string.type_sale
//                }))
    }
    */
}
