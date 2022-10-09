package by.asw.craft.sellers

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.asw.craft.DataModel

/**
 * Simple ViewModel factory that provides the MarsProperty and context to the ViewModel.
 */
class SellersViewModelFactory(
        private val catalorType: DataModel,
        private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SellersViewModel::class.java)) {
            return SellersViewModel(catalorType, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
