package com.example.origin.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.core.data.entity.location.Result
import com.example.origin.databinding.LocationFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationFragment : Fragment() {

    lateinit var binding: LocationFragmentBinding
    private val viewModel: LocationViewModel by viewModels()
    private lateinit  var adapter : LocationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = LocationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getOrigins()
    }

    private fun getOrigins() {
        viewModel.getOrigins()
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.origins.collect { resource ->
                when (resource) {
                    is com.example.core.di.networking.Resource.Loading -> {

                    }
                    is com.example.core.di.networking.Resource.Success -> {
                        Log.v("MyApp","data size : ${resource.data!!.results.size}")
                        setupRecyclerView(resource.data!!.results)
                    }
                    is com.example.core.di.networking.Resource.Error -> {

                    }
                }
            }
        }

    }

    private fun setupRecyclerView(data: ArrayList<Result>) {
        adapter = LocationAdapter()
        adapter.setOrigins(data)
        binding.recyclerView.adapter = adapter
    }

}