package com.example.origin.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.origin.databinding.ItemLocationListBinding
import com.example.core.data.entity.location.Result


class LocationAdapter : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    private lateinit var originList :ArrayList<Result>

    fun setOrigins(originList :ArrayList<Result>){
        this.originList = originList
    }

    inner class LocationViewHolder(private val binding: ItemLocationListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(origin : Result){
            binding.apply {
                locationName.text = origin.name
                locationType.text = origin.type
                locationDimension.text = origin.dimension
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding = ItemLocationListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LocationViewHolder(binding)
    }

    override fun getItemCount() = originList.size

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {

        holder.bind(originList[position])
    }


}