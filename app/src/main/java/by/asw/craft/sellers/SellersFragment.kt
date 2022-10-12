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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.asw.craft.R
import by.asw.craft.databinding.FragmentPage2Binding
import by.asw.craft.db.SearchRequest
import by.asw.craft.db.SearchRequestDatabase
import by.asw.craft.main.MainFragmentDirections
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
 * Use the [SellersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SellersFragment : Fragment() {


//    private lateinit var adapter: NotesRVAdapter
    private val noteDatabase by lazy { SearchRequestDatabase.getDatabase( getContext()).searchRequestDao() }

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
        val binding = FragmentPage2Binding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        val application = requireNotNull(activity).application
        val catalogueTypes = SellersFragmentArgs.fromBundle(arguments!!).catalogueType

        binding.photosGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })

        val viewModelFactory = SellersViewModelFactory(catalogueTypes, application)
        binding.viewModel = ViewModelProvider( this, viewModelFactory).get(CommonViewModel::class.java)


        // Observe the navigateToSelectedProperty LiveData and Navigate when it isn't null
        // After navigating, call displayPropertyDetailsComplete() so that the ViewModel is ready
        // for another navigation event.
        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                // Must find the NavController from the Fragment
                this.findNavController().navigate(SellersFragmentDirections.actionPage2ToAssortment(it))
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.displayPropertyDetailsComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    /**
     * Inflates the overflow menu that contains filtering options.
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    /**
     * Updates the filter in the [OverviewViewModel] when the menu items are selected from the
     * overflow menu.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.offers_search_menu -> {
                val intent = Intent(context, SearchDialog::class.java)
                newSearchRequestLauncher.launch(intent)
            }
            R.id.offers_search_menu_history -> {
                this.findNavController().navigate(SellersFragmentDirections.actionViewSearchHistory(viewModel.currentCatalog))
            }
            else -> {}
        }
        return true
    }

    private val newSearchRequestLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val searchText = result.data?.getStringExtra("search_text")
                // Add the new note at the top of the list
                val newNote = SearchRequest(Date(), searchText ?: "")
                lifecycleScope.launch {
                    noteDatabase.addRecord(newNote)
                }
            }
        }
}