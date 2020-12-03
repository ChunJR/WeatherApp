package com.dungle.weatherapp.main.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dungle.weatherapp.R
import com.dungle.weatherapp.data.model.WeatherInfoModel
import kotlinx.android.extensions.LayoutContainer

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    var data = listOf<WeatherInfoModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false))
    }

    override fun onBindViewHolder(holder: WeatherAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(weatherInfoModel: WeatherInfoModel) {
            // TODO show list weather
        }
    }
}