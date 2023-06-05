package com.example.bnitest.helper


import androidx.fragment.app.Fragment
import com.example.bnitest.view.activity.MainActivity
import com.example.bnitest.R
import com.google.android.material.bottomnavigation.BottomNavigationView


fun Fragment.hideBottomNavigationView(){
    val bottomNavigationView =
        (activity as MainActivity).findViewById<BottomNavigationView>(
           R.id.bottomNavigation
        )
    bottomNavigationView.visibility = android.view.View.GONE
}

fun Fragment.showBottomNavigationView(){
    val bottomNavigationView =
        (activity as MainActivity).findViewById<BottomNavigationView>(
          R.id.bottomNavigation
        )
    bottomNavigationView.visibility = android.view.View.VISIBLE
}