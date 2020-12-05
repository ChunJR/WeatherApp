package com.dungle.weatherapp.data.source.database

import androidx.room.*
import com.dungle.weatherapp.data.model.Area


@Dao
interface AreaDao {
    @Query("SELECT * FROM area_table WHERE name LIKE :cityName")
    fun findCityByName(cityName: String): Area

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertArea(area: Area): Long

    @Update
    fun updateArea(area: Area)

    @Delete
    fun deleteArea(area: Area)

    @Transaction
    fun saveToLocal(area: Area) {
        val id: Long = insertArea(area)
        if (id == -1L) {
            updateArea(area)
        }
    }
}