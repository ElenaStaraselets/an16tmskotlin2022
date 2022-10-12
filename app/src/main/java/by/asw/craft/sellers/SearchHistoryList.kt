package com.example.roomexample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.asw.craft.R
import by.asw.craft.databinding.FragmentPage2Binding
import by.asw.craft.databinding.FragmentSearchHistoryBinding
import by.asw.craft.databinding.SearchHistoryViewItemBinding
import by.asw.craft.db.SearchRequest
import by.asw.craft.db.SearchRequestDatabase
import by.asw.craft.main.PhotoGridAdapter
import by.asw.craft.sellers.*
import kotlinx.android.synthetic.main.activity_notes.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*


class SearchHistoryList : Fragment() {

    private lateinit var adapter: NotesRVAdapter

    /**
     * Lazily initialize our [OverviewViewModel].
     */
    private val viewModel: CommonViewModel by lazy {
        ViewModelProvider(this).get(CommonViewModel::class.java)
    }

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the OverviewFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentSearchHistoryBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        val application = requireNotNull(activity).application

        binding.searchHistoryGrid.adapter = SearchListAdapter(SearchListAdapter.OnClickListener {
            viewModel.displaySearchItemDetails(it)
        })

        val viewModelFactory = SearchHistioryViewModelFactory(application)
        binding.viewModel = ViewModelProvider( this, viewModelFactory).get(CommonViewModel::class.java)
//        return binding.root

        // Sets the adapter of the photosGrid RecyclerView with clickHandler lambda that
        // tells the viewModel when our property is clicked
//        binding.photosGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener {
//            viewModel.displayPropertyDetails(it)
//        })

        // Observe the navigateToSelectedProperty LiveData and Navigate when it isn't null
        // After navigating, call displayPropertyDetailsComplete() so that the ViewModel is ready
        // for another navigation event.
//        viewModel.repeaterequest.observe(viewLifecycleOwner, Observer {
//            if ( null != it ) {
//            }
//        })
        setHasOptionsMenu(true)
        return binding.root
    }
}
