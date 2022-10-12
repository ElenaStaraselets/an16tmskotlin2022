package by.asw.craft.assortment

import android.app.Application
import androidx.lifecycle.*
import by.asw.craft.network.CatalogItem


class AssortmentViewModel( item: CatalogItem, app: Application) : AndroidViewModel(app) {

    // The internal MutableLiveData for the selected property
    private val _selectedProperty = MutableLiveData<CatalogItem>()

    // The external LiveData for the SelectedProperty
    val selectedProperty: LiveData<CatalogItem>
        get() = _selectedProperty

    // Initialize the _selectedProperty MutableLiveData
    init {
        _selectedProperty.value = item
    }

}

