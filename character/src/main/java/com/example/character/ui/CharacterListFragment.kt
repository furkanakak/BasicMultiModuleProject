package com.example.character.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.character.databinding.CharacterListFragmentBinding
import com.example.core.data.entity.character.Result
import com.example.core.di.networking.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterListFragment: Fragment()  {
    lateinit var binding: CharacterListFragmentBinding
    private val viewModel: CharacterViewModel by viewModels()
    lateinit var adapter : CharacterRecyclerAdapter

    @Inject
    lateinit var navigation : CharacterNavigation

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = CharacterListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCharacters()


    }

    private fun getCharacters() {
        viewModel.getCharacters(1)
        Log.v("getCharacters","getCharacters methodu")
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.characters.collect{  resource ->
                when(resource){
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        Log.d("MyApp", "Data: ${resource.data}")
                        setupRecyclerAdapter(resource.data?.results)
                    }
                    is Resource.Error -> {


                    }
                }
            }
        }
    }

    private fun setupRecyclerAdapter(data: ArrayList<Result>?) {
        adapter = CharacterRecyclerAdapter()
        adapter.setCharacters(data?: ArrayList())
        adapter.setCharacterOnclick(::itemOnClick)
        binding.recyclerView.adapter = adapter
    }

    private fun itemOnClick(result: Result) {
        navigation.navigateToDetail(requireActivity())
        Log.v("deeeee","item basildi")
    }

}