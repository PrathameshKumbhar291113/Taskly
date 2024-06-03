package com.prathameshkumbhar.taskly.common

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.prathameshkumbhar.taskly.databinding.ActivitySplashBinding
import com.prathameshkumbhar.taskly.features.home_screen.presentation.screen.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import splitties.activities.start

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUi()

    }

    private fun setupUi() {
        
        lifecycleScope.launch {
            delay(3000)
            start<HomeActivity>()
        }

    }
}