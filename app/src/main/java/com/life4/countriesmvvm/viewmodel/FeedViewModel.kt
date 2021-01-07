package com.life4.countriesmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.life4.countriesmvvm.model.Country

class FeedViewModel : ViewModel() {

    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()


    fun refreshData(){

        val country = Country("Turkey","Asia","Ankara","TRY","Turkish","https://ss.com")
        val country2 = Country("USA","America","Washington","USD","English","ss.com")
        val country3 = Country("Germany","Europe","Berlin","Euro","German","ss.com")

        val countryList = arrayListOf(country,country2,country3)
        countries.value = countryList
        countryError.value = false
        countryLoading.value = false

    }




}