package com.dungle.weatherapp.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dungle.weatherapp.R
import com.dungle.weatherapp.data.model.Status
import com.dungle.weatherapp.main.viewmodel.WeatherInfoViewModel
import com.dungle.weatherapp.util.getViewModelFactory
import com.dungle.weatherapp.util.widgets.CustomItemDivider
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {
    private val viewModel: WeatherInfoViewModel by viewModels { getViewModelFactory() }
    private val adapter = WeatherAdapter()
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
        context?.let {
            val customItemDivider =
                CustomItemDivider(ContextCompat.getDrawable(it, R.drawable.divider)!!)
            rcvWeather.adapter = adapter
            rcvWeather.addItemDecoration(customItemDivider)
            rcvWeather.layoutManager = LinearLayoutManager(it)
        }
    }

    private fun observeDataChanged() {
        viewModel.areaData.observe(viewLifecycleOwner, { dataResult ->
            when (dataResult.status) {
                Status.SUCCESS -> {
                    hideLoading()
                    dataResult.data?.weatherInfoList?.let {
                        adapter.data = it
                    }
                }

                Status.ERROR -> {
                    hideLoading()
                    showError(dataResult.message)
                }

                else -> {
                    showLoading()
                }
            }
        })
    }

    private fun showError(message: String? = "Some thing went wrong") {
        context?.let {
            Toast.makeText(
                it,
                message,
                Toast.LENGTH_SHORT
            ).show()
        }
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