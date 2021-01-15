package com.life4.countriesmvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.life4.countriesmvvm.R
import com.life4.countriesmvvm.databinding.ItemCountryBinding
import com.life4.countriesmvvm.model.Country
import com.life4.countriesmvvm.utils.downloadImageFromUrl
import com.life4.countriesmvvm.utils.placeHolderProgressBar
import com.life4.countriesmvvm.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.item_country.view.*
import java.util.ArrayList

class CountryAdapter(var countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(), CountryClickListener {


    class CountryViewHolder(var view: ItemCountryBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemCountryBinding>(
            inflater,
            R.layout.item_country,
            parent,
            false
        )
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.country = countryList[position]
        holder.view.listener = this
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClicked(v: View) {
        val countryUuid = v.countryUuidText.text.toString().toInt()
        val action = FeedFragmentDirections.actionFeedFragmentToCountryDetailsFragment(countryUuid)
        Navigation.findNavController(v).navigate(action)

    }
}