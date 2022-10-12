package by.asw.craft.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import by.asw.craft.R
import by.asw.craft.assortment.MapViewModelFactory
import by.asw.craft.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*


class MapFragment : Fragment(), OnMapReadyCallback {
    lateinit var  mMap : GoogleMap

    private val viewModel: MapViewModel by lazy {
        ViewModelProvider(this).get(MapViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)


        val binding = FragmentMapBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val item = MapFragmentArgs.fromBundle(arguments!!).selectedItem
        val viewModelFactory = MapViewModelFactory(item, application)
        binding.viewModel = ViewModelProvider(
                this, viewModelFactory).get(MapViewModel::class.java)

        return binding.root
    }

    override fun onMapReady(p0: GoogleMap) {
        if(viewModel.selectedProperty.value!=null) {
            val pnt: LatLng =
                LatLng(viewModel.selectedProperty.value!!.lat, viewModel.selectedProperty.value!!.lon);
            mMap.addMarker(
                MarkerOptions()
                    .position(pnt)
                    .title(viewModel.selectedProperty.value?.itemname)
            )
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pnt, 15.0f));
        }
    }
}
