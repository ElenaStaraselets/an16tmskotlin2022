package by.asw.craft.sellers

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.asw.craft.R
import by.asw.craft.databinding.FragmentPage2Binding
import by.asw.craft.databinding.FragmentSearchHistoryBinding
import by.asw.craft.db.SearchRequest
import by.asw.craft.db.SearchRequestDatabase
import by.asw.craft.main.PhotoGridAdapter
import kotlinx.android.synthetic.main.activity_notes.*
import kotlinx.coroutines.launch
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchHistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchHistoryFragment : Fragment() {

    private val noteDatabase by lazy {
        SearchRequestDatabase.getDatabase( getContext()).searchRequestDao()
    }

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


        //******************************************************
        val application = requireNotNull(activity).application
        val catalogueTypes = SellersFragmentArgs.fromBundle(arguments!!).catalogueType


        binding.searchHistoryGrid.adapter = SearchListAdapter(SearchListAdapter.OnClickListener {
            viewModel.repeateSearchValue(it)
        })



        val viewModelFactory = SellersViewModelFactory(catalogueTypes, application)
        binding.viewModel = ViewModelProvider( this, viewModelFactory).get(CommonViewModel::class.java)


        // Observe the navigateToSelectedProperty LiveData and Navigate when it isn't null
        // After navigating, call displayPropertyDetailsComplete() so that the ViewModel is ready
        // for another navigation event.
        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                // Must find the NavController from the Fragment
//                this.findNavController().navigate(SellersFragmentDirections.actionPage2ToAssortment(it))
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.displayPropertyDetailsComplete()
            }
        })
        setHasOptionsMenu(false)
        return binding.root
    }
}