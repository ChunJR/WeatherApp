package com.dungle.weatherapp.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.dungle.weatherapp.R
import com.dungle.weatherapp.main.viewmodel.WeatherInfoViewModel
import com.dungle.weatherapp.util.getViewModelFactory

class MainFragment : Fragment() {
    private val viewModel: WeatherInfoViewModel by viewModels { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initUI()
        addEvents()
        observeDataChanged()
    }

    private fun addEvents() {
        // TODO add click event when click to button to get data
        viewModel.getWeatherInfo("something")
    }

    private fun initUI() {
        // TODO Add adapter
    }

    private fun observeDataChanged() {
        viewModel.weatherInfoData.observe(viewLifecycleOwner, { data ->
            //TODO notify data to adapter
        })
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}