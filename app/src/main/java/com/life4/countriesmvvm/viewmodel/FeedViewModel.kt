package com.life4.countriesmvvm.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.life4.countriesmvvm.model.Country
import com.life4.countriesmvvm.service.CountryAPIService
import com.life4.countriesmvvm.service.CountryDatabase
import com.life4.countriesmvvm.utils.CustomSharedPreferences
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application) : BaseViewModel(application) {

    private val countryAPIService = CountryAPIService()
    private val disposable = CompositeDisposable()
    private var customSharedPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()


    fun refreshData() {

        val updateTime = customSharedPreferences.getTime()
        if (updateTime != 0L && updateTime != null && System.nanoTime() - updateTime < refreshTime) {
            getDataFromSQL()
        } else {
            getDataFromAPI()

        }


    }

    fun refreshDataSwipe() {
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        countryLoading.value = true

        disposable.add(
            countryAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                    override fun onSuccess(t: List<Country>) {
                        storeInSQLite(t)
                        Toast.makeText(getApplication(), "Countries From API", Toast.LENGTH_LONG)
                            .show()
                    }

                    override fun onError(e: Throwable) {
                        countryLoading.value = false
                        countryError.value = true
                        e.printStackTrace()
                    }


                })
        )


    }

    private fun getDataFromSQL() {
        countryLoading.value = true

        launch {
            var listCountries = CountryDatabase(getApplication()).countryDao().getAllCountries()
            showCountries(listCountries)
            Toast.makeText(getApplication(), "Countries From SQLite", Toast.LENGTH_LONG).show()
        }


    }

    private fun showCountries(countryList: List<Country>) {
        countryLoading.value = false
        countries.value = countryList
        countryError.value = false
    }

    private fun storeInSQLite(list: List<Country>) {
        launch {

            val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()
            val listLong = dao.insertAll(*list.toTypedArray())
            var i = 0;
            while (i < list.size) {

                list[i].uuid = listLong[i].toInt()
                i = i + 1
            }
            showCountries(list)
        }
        customSharedPreferences.saveTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }

}