package com.example.basicmultimoduleproject.impl


import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation.findNavController
import com.example.basicmultimoduleproject.R


import com.example.character.ui.CharacterNavigation
import com.example.core.data.entity.character.Result

class CharacterNavigationImpl : CharacterNavigation {
    override fun navigateToDetail(view: View,result: Result) {
        val bundle = Bundle().apply {
            putParcelable("result", result)
        }
       findNavController(view).navigate(R.id.action_characterListFragment_to_detailDialogFragment,bundle)
    }
}