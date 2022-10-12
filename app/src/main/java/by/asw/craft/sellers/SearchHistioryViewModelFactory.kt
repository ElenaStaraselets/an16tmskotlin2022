package by.asw.craft.sellers

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.asw.craft.db.SearchRequest


class SearchHistioryViewModelFactory(
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchRequest::class.java)) {
            return CommonViewModel(null, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
