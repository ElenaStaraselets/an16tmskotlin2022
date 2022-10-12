package by.asw.craft.assortment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.asw.craft.App
import by.asw.craft.databinding.FragmentAssortmentBinding
import by.asw.craft.main.PhotoGridAdapter
import by.asw.craft.sellers.CommonViewModel
import by.asw.craft.sellers.SellersFragmentDirections
import kotlinx.android.synthetic.main.activity_new_search.*
import kotlinx.android.synthetic.main.fragment_assortment.*

class Assortment : Fragment() {

    private val viewModel: AssortmentViewModel by lazy {
        ViewModelProvider(this).get(AssortmentViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application
        val binding = FragmentAssortmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val item = AssortmentArgs.fromBundle(arguments!!).selectedItem
        val viewModelFactory = AssortmentViewModelFactory(item, application)
        binding.viewModel = ViewModelProvider(
                this, viewModelFactory).get(AssortmentViewModel::class.java)


        binding.mapNoteButton.setOnClickListener(View.OnClickListener {
            this.findNavController().navigate(AssortmentDirections.actionAssortmentToMap(item))
        })


        binding.mainPhotoImage.setImageDrawable(App.appContext.resources.getDrawable(item.imageID))

        return binding.root
    }
}