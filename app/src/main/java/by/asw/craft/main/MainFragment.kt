package by.asw.craft.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import by.asw.craft.AutoFitGridLayoutManager
import by.asw.craft.DataModel
import by.asw.craft.R
import by.asw.craft.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() , RecyclerViewAdapter.ItemListener {

//    var arrayList: ArrayList<DataModel>? = null

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        val adapter = RecyclerViewAdapter( context, viewModel.date, this)
        recyclerView.setAdapter(adapter)
        /**
         * AutoFitGridLayoutManager that auto fits the cells by the column width defined.
         */
        val layoutManager = AutoFitGridLayoutManager(context, 500)
        recyclerView.setLayoutManager(layoutManager)
    }

    override fun onItemClick(item: DataModel?) {
        if(item!== null)
            this.findNavController().navigate(MainFragmentDirections.actionMainFragmentToSellers(item))

        Toast.makeText(
            getContext(),
            item?.text.toString() + " is clicked",
            Toast.LENGTH_SHORT
        ).show()
    }
}
