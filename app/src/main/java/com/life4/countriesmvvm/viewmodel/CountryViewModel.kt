package com.life4.countriesmvvm.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.life4.countriesmvvm.model.Country
import com.life4.countriesmvvm.service.CountryDatabase
import kotlinx.coroutines.launch

class CountryViewModel(application: Application) : BaseViewModel(application) {

    val countryLiveData = MutableLiveData<Country>()


    fun getDataFromRoom(uuid:Int) {
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
           var country = dao.getCountry(uuid)
            countryLiveData.value = country
        }


    }
}