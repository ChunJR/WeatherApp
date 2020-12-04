package com.dungle.weatherapp.util

import com.dungle.weatherapp.data.model.ResponseHandler
import androidx.fragment.app.Fragment
import com.dungle.weatherapp.BaseApplication
import com.dungle.weatherapp.ViewModelFactory

fun Fragment.getViewModelFactory(): ViewModelFactory {
    val repo = (requireContext().applicationContext as BaseApplication).dataRepositoryImpl
    return ViewModelFactory(repo, ResponseHandler())
}