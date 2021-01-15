package com.life4.countriesmvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.life4.countriesmvvm.R
import com.life4.countriesmvvm.databinding.FragmentCountryDetailsBinding
import com.life4.countriesmvvm.utils.downloadImageFromUrl
import com.life4.countriesmvvm.utils.placeHolderProgressBar
import com.life4.countriesmvvm.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.fragment_country_details.*


class CountryDetailsFragment : Fragment() {

    lateinit var viewModel: CountryViewModel
    private var countryUuid = 0
    private lateinit var dataBinding :FragmentCountryDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_country_details,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid = CountryDetailsFragmentArgs.fromBundle(it).countryUuid

        }

        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)

        observeLiveData()

    }

    private fun observeLiveData() {

        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
               dataBinding.country = country

            }
        })

    }

}