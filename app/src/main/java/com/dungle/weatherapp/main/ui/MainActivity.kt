package com.dungle.weatherapp.main.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dungle.weatherapp.R
import com.dungle.weatherapp.util.replaceFragmentInActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupFragmentView()
    }

    private fun setupFragmentView() {
        supportFragmentManager.findFragmentById(R.id.fragmentContainer)
            ?: replaceFragmentInActivity(MainFragment.newInstance(), R.id.fragmentContainer)
    }
}