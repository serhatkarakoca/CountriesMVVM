package com.life4.countriesmvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.life4.countriesmvvm.R
import com.life4.countriesmvvm.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.fragment_country_details.*


class CountryDetailsFragment : Fragment() {

    lateinit var viewModel: CountryViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom()

        observeLiveData()

    }

    private fun observeLiveData() {

        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                countryName.text = it.countryName
                countryRegion.text = it.countryRegion
                countryCapital.text = it.countryCapital
                countryLanguage.text = it.countryCurrency
                countryCurrency.text = it.countryCurrency
            }
        })

    }

}