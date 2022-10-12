package by.asw.craft.assortment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.asw.craft.network.CatalogItem
import by.asw.craft.map.MapViewModel



class MapViewModelFactory(
    private val item: CatalogItem,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MapViewModel::class.java)) {
            return MapViewModel(item, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
