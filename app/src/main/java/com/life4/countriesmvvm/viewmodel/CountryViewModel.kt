package com.life4.countriesmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.life4.countriesmvvm.model.Country

class CountryViewModel : ViewModel() {

    val countryLiveData = MutableLiveData<Country>()


    fun getDataFromRoom(){
        val country = Country("Turkey","Asia","Ankara","TRY","Turkish","https://ss.com")
        countryLiveData.value = country
    }
}