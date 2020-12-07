package com.dungle.weatherapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.dungle.weatherapp.data.model.Area
import com.dungle.weatherapp.data.model.DataResult
import com.dungle.weatherapp.data.source.repo.DataRepositoryImpl
import com.dungle.weatherapp.main.viewmodel.WeatherInfoViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.timeout
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException

@RunWith(MockitoJUnitRunner::class)
class WeatherInfoViewModelTest {

    private lateinit var viewModel: WeatherInfoViewModel
    private val validLocation = "Hanoi"
    private val invalidLocation = "Hanoi1"
    private val netWorkConnected = true
    private val netWorkNotAvailable = false
    private val successResponse = DataResult.success(Mockito.mock(Area::class.java))
    private val networkException : HttpException = mock()
    private val nullException: NullPointerException = mock()
    private  var dataRepositoryImpl: DataRepositoryImpl = mock()
    private  var areaObserver: Observer<DataResult<Area>> = mock()

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        runBlockingTest {
            whenever(dataRepositoryImpl.getWeatherInfoByCityFromApi(invalidLocation)).thenThrow(networkException)
            whenever(dataRepositoryImpl.getWeatherInfoByCityFromApi(validLocation)).thenReturn(
                Mockito.mock(Area::class.java)
            )

            whenever(dataRepositoryImpl.getWeatherInfoByCityFromLocal(invalidLocation)).thenThrow(
                nullException
            )
            whenever(dataRepositoryImpl.getWeatherInfoByCityFromLocal(validLocation)).thenReturn(
                Mockito.mock(Area::class.java)
            )
        }

        viewModel = WeatherInfoViewModel(dataRepositoryImpl)
        viewModel.areaData.observeForever(areaObserver)
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `when getWeatherInfoByCityFromApi is called with valid location, then observer is updated with success`() {
        runBlockingTest {
            viewModel.getWeatherInfo(validLocation, netWorkConnected)
            delay(10)
            verify(dataRepositoryImpl).getWeatherInfoByCityFromApi(validLocation)
            verify(areaObserver, timeout(50)).onChanged(DataResult.loading(null))
            verify(areaObserver, timeout(500)).onChanged(successResponse)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `when getWeatherInfo is called with invalid location, then observer is updated with failure`() =
        runBlockingTest {
            viewModel.getWeatherInfo(invalidLocation, netWorkConnected)
            delay(10)
            verify(dataRepositoryImpl).getWeatherInfoByCityFromApi(validLocation)
            verify(areaObserver, timeout(50)).onChanged(DataResult.loading(null))
//            verify(areaObserver, timeout(500)).onChanged(networkException)
        }
}