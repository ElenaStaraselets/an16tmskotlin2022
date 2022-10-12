package by.asw.craft.assortment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.asw.craft.network.CatalogItem


class AssortmentViewModelFactory(
    private val item: CatalogItem,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AssortmentViewModel::class.java)) {
            return AssortmentViewModel(item, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
