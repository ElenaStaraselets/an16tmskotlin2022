package by.asw.craft.sellers

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.*
import by.asw.craft.DataModel
import by.asw.craft.R
import by.asw.craft.db.SearchRequest
import by.asw.craft.db.SearchRequestDatabase
import by.asw.craft.di.RepositoryModule
import by.asw.craft.network.CatalogItem
import by.asw.craft.network.CommonCraftApiFilter
import by.asw.craft.network.CraftTransportApi.retrofitService
import com.squareup.moshi.Json
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

enum class ImageLoadedStatus { LOADING, ERROR, DONE }

class CommonViewModel(catalogueType: DataModel?, app: Application) : AndroidViewModel(app) {

    @Inject
    lateinit var networkRepo: RepositoryModule

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<ImageLoadedStatus>()
    private val noteDatabase by lazy { SearchRequestDatabase.getDatabase( app.applicationContext).searchRequestDao() }

    private val ctx:Context = app.applicationContext

    // The external immutable LiveData for the request status
    val status: LiveData<ImageLoadedStatus>
        get() = _status


    private val _properties = MutableLiveData<List<CatalogItem>>()

    private val _searchhistiory = MutableLiveData<List<SearchRequest>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val properties: LiveData<List<CatalogItem>>
        get() = _properties

    // Cached data from db
    val searchhistiory: LiveData<List<SearchRequest>>
        get() = _searchhistiory

    // Internally, we use a MutableLiveData to handle navigation to the selected property
    private val _navigateToSelectedProperty = MutableLiveData<CatalogItem>()

    private val _repeaterequest = MutableLiveData<SearchRequest>()

    // The external immutable LiveData for the navigation property
    val navigateToSelectedProperty: LiveData<CatalogItem>
        get() = _navigateToSelectedProperty

    val repeaterequest: LiveData<SearchRequest>
        get() = _repeaterequest


    var currentCatalog:DataModel
    /**
     * Call getCatalogueDataByParams() on init so we can display status immediately.
     */
    init {
        currentCatalog = catalogueType!!
        getCatalogueDataByParams(CommonCraftApiFilter.SHOW_ALL)
        getSearchHistoryItems()
    }


    private fun getCatalogueDataByParams(filter: CommonCraftApiFilter) {
        viewModelScope.launch {
            _status.value = ImageLoadedStatus.LOADING
            try {
//                _properties.value = networkRepo.provideNetworkRepo(filter.value, currentCatalog.catalogueType)
                _properties.value = retrofitService.getProperties(filter.value, currentCatalog.catalogueType)
                _status.value = ImageLoadedStatus.DONE
            } catch (e: Exception) {
                _status.value = ImageLoadedStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }

    fun getSearchHistoryItems() {
        viewModelScope.launch {
            _status.value = ImageLoadedStatus.LOADING
            try {

                noteDatabase.getAllSearches().collect { notesList ->
                    if (notesList.isNotEmpty()) {
                        _searchhistiory.value = notesList
                    }
                    else{
                        _searchhistiory.value = ArrayList()
                    }
                }

                _status.value = ImageLoadedStatus.DONE
            } catch (e: Throwable) {
                _status.value = ImageLoadedStatus.ERROR
                _searchhistiory.value = ArrayList()
            }
        }
    }

    fun displayPropertyDetails(catalogProperty: CatalogItem) {
        _navigateToSelectedProperty.value = catalogProperty
    }

    fun repeateSearchValue(item: SearchRequest) {
        _repeaterequest.value = item
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    fun updateFilter(filter: CommonCraftApiFilter) {
        getCatalogueDataByParams(filter)
    }


    fun displaySearchItemDetails(item: SearchRequest) {
        _repeaterequest.value = item
    }

    fun displaySearchItemDetailsComplete() {
        _repeaterequest.value = null
    }


}
