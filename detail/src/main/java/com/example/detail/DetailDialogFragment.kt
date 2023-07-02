package com.example.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import coil.load
import com.example.core.data.entity.character.Result
import com.example.detail.databinding.DetailDialogFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailDialogFragment : DialogFragment() {
    lateinit var binding : DetailDialogFragmentBinding
    var result : Result? = null
    override fun onStart() {
        super.onStart()
        dialog?.let {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            it.window?.setLayout(width, height)
        }
        isCancelable = true

        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DetailDialogFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = arguments?.getParcelable("result")
        setupUi()
    }

    private fun setupUi() {
        result?.let {
            binding.apply {
                name.text = it.name
                status.text = it.status
                origin.text = it.origin.name
                image.load(it.image)
            }
        }
    }

}