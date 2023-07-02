package com.example.basicmultimoduleproject.impl

import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.example.character.ui.CharacterNavigation

class CharacterNavigationImpl : CharacterNavigation {
    override fun navigateToDetail(activity: Activity) {
        Log.v("deeeee","item basildi impl")
        Toast.makeText(activity, "item basildi", Toast.LENGTH_SHORT).show()
    }
}