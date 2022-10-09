package by.asw.craft.sellers

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.asw.craft.R
import by.asw.craft.databinding.FragmentPage2Binding
import by.asw.craft.network.CommonCraftApiFilter
import by.asw.craft.ui.main.PhotoGridAdapter

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
    /*
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page2, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Page2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Sellers().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

     */
    /**
     * Lazily initialize our [SellersViewModel].

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentPage2Binding.inflate(inflater)
        binding.lifecycleOwner = this
        val catalogueTypes = SellersFragmentArgs.fromBundle(arguments!!).catalogueType
        val viewModelFactory = SellersViewModelFactory(catalogueTypes, application)
        binding.viewModel = ViewModelProvider( this, viewModelFactory).get(SellersViewModel::class.java)
        return binding.root
    }

    */



    /**
     * Lazily initialize our [OverviewViewModel].
     */
    private val viewModel: SellersViewModel by lazy {
        ViewModelProvider(this).get(SellersViewModel::class.java)
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

//        // Giving the binding access to the OverviewViewModel
//        binding.viewModel = viewModel


        val application = requireNotNull(activity).application
        val catalogueTypes = SellersFragmentArgs.fromBundle(arguments!!).catalogueType

        binding.photosGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })

        val viewModelFactory = SellersViewModelFactory(catalogueTypes, application)
        binding.viewModel = ViewModelProvider( this, viewModelFactory).get(SellersViewModel::class.java)
//        return binding.root

        // Sets the adapter of the photosGrid RecyclerView with clickHandler lambda that
        // tells the viewModel when our property is clicked
//        binding.photosGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener {
//            viewModel.displayPropertyDetails(it)
//        })

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
        viewModel.updateFilter(
            when (item.itemId) {
                R.id.show_rent_menu -> CommonCraftApiFilter.SHOW_RENT
                R.id.show_buy_menu -> CommonCraftApiFilter.SHOW_BUY
                else -> CommonCraftApiFilter.SHOW_ALL
            }
        )
        return true
    }
}