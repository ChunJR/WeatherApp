package com.dungle.weatherapp.main.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dungle.weatherapp.R
import com.dungle.weatherapp.data.model.WeatherInfoModel
import com.dungle.weatherapp.util.TimeUtils
import com.dungle.weatherapp.util.UnitUtils
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_weather.*

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    var data = listOf<WeatherInfoModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(weatherInfoModel: WeatherInfoModel) {
            tvDate?.text = containerView.context.getString(
                R.string.txt_date,
                TimeUtils.convertMillsToDateTimeAsString(
                    TimeUtils.PATTERN,
                    weatherInfoModel.dateTime
                )
            )
            tvAverageTemperature?.text = containerView.context.getString(
                R.string.txt_average_temperature,
                UnitUtils.getRoundedNumber(weatherInfoModel.temp.getAveragePerDay())
            )
            tvPressure?.text = containerView.context.getString(
                R.string.txt_pressure,
                weatherInfoModel.pressure
            )
            tvHumidity?.text = containerView.context.getString(
                R.string.txt_humidity,
                weatherInfoModel.humidity
            )
            tvDescription?.text = containerView.context.getString(
                R.string.txt_description,
                weatherInfoModel.weather[0].description
            )
        }
    }
}