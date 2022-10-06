package by.asw.craft.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import by.asw.craft.AutoFitGridLayoutManager
import by.asw.craft.DataModel
import by.asw.craft.R
import by.asw.craft.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.ArrayList

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
        // TODO: Use the ViewModel

       // recyclerView = findViewById(R.id.recyclerView) as RecyclerView?
//        arrayList = ArrayList<DataModel>()
//        arrayList!!.add(DataModel("Item 1", R.drawable.battle, "#09A9FF"))
//        arrayList!!.add(DataModel("Item 2", R.drawable.beer, "#3E51B1"))
//        arrayList!!.add(DataModel("Item 3", R.drawable.ferrari, "#673BB7"))
//        arrayList!!.add(DataModel("Item 4", R.drawable.jetpack_joyride, "#4BAA50"))
//        arrayList!!.add(DataModel("Item 5", R.drawable.three_d, "#F94336"))
//        arrayList!!.add(DataModel("Item 6", R.drawable.terraria, "#0A9B88"))
        val adapter = RecyclerViewAdapter( context, viewModel.date, this)
        recyclerView.setAdapter(adapter)
        /**
         * AutoFitGridLayoutManager that auto fits the cells by the column width defined.
         */
        val layoutManager = AutoFitGridLayoutManager(context, 500)
        recyclerView.setLayoutManager(layoutManager)
    }

    override fun onItemClick(item: DataModel?) {
        Toast.makeText(
            getContext(),
            item?.text.toString() + " is clicked",
            Toast.LENGTH_SHORT
        ).show()
    }
}
