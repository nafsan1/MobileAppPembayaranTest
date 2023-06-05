package com.example.bnitest.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bnitest.R
import com.example.bnitest.databinding.ActivityMainBinding
import com.example.core.repository.PreferencesRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var preferences: PreferencesRepository

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navController = findNavController(R.id.hostFragment)
        binding.bottomNavigation.setupWithNavController(navController)

        if (preferences.getSaldo() == 0){
            preferences.setSaldo(10000000)
        }
    }
}