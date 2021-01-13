package com.life4.countriesmvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.life4.countriesmvvm.R
import com.life4.countriesmvvm.model.Country
import com.life4.countriesmvvm.utils.downloadImageFromUrl
import com.life4.countriesmvvm.utils.placeHolderProgressBar
import com.life4.countriesmvvm.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.item_country.view.*
import java.util.ArrayList

class CountryAdapter(var countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {


    class CountryViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.tvCountryName.text = countryList[position].countryName
        holder.view.tvRegion.text = countryList[position].countryRegion

        holder.view.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryDetailsFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
        holder.view.imageView.downloadImageFromUrl(
            countryList[position].flag,
            placeHolderProgressBar(holder.view.context)
        )
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }
}