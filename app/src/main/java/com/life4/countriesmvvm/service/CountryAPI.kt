package com.life4.countriesmvvm.service

import com.life4.countriesmvvm.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {


    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountries(): Single<List<Country>>
}