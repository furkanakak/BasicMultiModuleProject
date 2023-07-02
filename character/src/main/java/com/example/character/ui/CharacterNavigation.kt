package com.example.character.ui

import android.view.View
import com.example.core.data.entity.character.Result

interface CharacterNavigation {
    fun navigateToDetail(view: View,result: Result)
}