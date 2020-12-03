package com.dungle.weatherapp.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.dungle.weatherapp.R
import com.dungle.weatherapp.data.model.DataResult
import com.dungle.weatherapp.main.viewmodel.WeatherInfoViewModel
import com.dungle.weatherapp.util.getViewModelFactory
import kotlinx.android.synthetic.main.main_fragment.*

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
        btnGetWeather?.setOnClickListener {
            viewModel.getWeatherInfo(edCityName?.text.toString())
        }
    }

    private fun initUI() {
        // TODO Add adapter
    }

    private fun observeDataChanged() {
        viewModel.weatherInfoData.observe(viewLifecycleOwner, { dataResult ->
            when (dataResult) {
                is DataResult.Success -> {
                    hideLoading()
                }

                is DataResult.Error -> {
                    hideLoading()
                    showError(dataResult.exception)
                }

                is DataResult.Loading -> {
                    showLoading()
                }
            }
        })
    }

    private fun showError(exception: Exception) {
        Toast.makeText(
            requireContext(), if (exception.message != null) {
                exception.message
            } else {
                "Something went wrong"
            }, Toast.LENGTH_SHORT
        ).show()
    }

    private fun hideLoading() {
        pbLoading?.visibility = View.GONE
    }

    private fun showLoading() {
        pbLoading?.visibility = View.VISIBLE
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}