package com.dungle.weatherapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dungle.weatherapp.data.model.Area
import com.dungle.weatherapp.data.source.repo.DataRepositoryImpl
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
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
class DataRepositoryImplTest {
    private lateinit var repo: DataRepositoryImpl
    private val validLocation = "Hanoi"
    private val invalidLocation = "Hanoi1"
    private val successResponse = Mockito.mock(Area::class.java)
    private val networkException: HttpException = mock()
    private val nullException: NullPointerException = mock()

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
        whenever(networkException.code()).thenReturn(404)
        repo = mock()
        runBlockingTest {
            whenever(repo.getWeatherInfoByCityFromApi(invalidLocation)).thenThrow(networkException)
            whenever(repo.getWeatherInfoByCityFromApi(validLocation)).thenReturn(successResponse)

            whenever(repo.getWeatherInfoByCityFromLocal(invalidLocation)).thenThrow(nullException)
            whenever(repo.getWeatherInfoByCityFromLocal(validLocation)).thenReturn(successResponse)
        }
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
    fun `when getWeatherInfoByCityFromApi is called with valid location, then area is returned`() =
        runBlockingTest {
            assertEquals(successResponse, repo.getWeatherInfoByCityFromApi(validLocation))
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `when getWeatherInfoByCityFromApi is called with invalid location, then error is returned`() =
        runBlockingTest {
            assertEquals(networkException, repo.getWeatherInfoByCityFromApi(invalidLocation))
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `when getWeatherInfoByCityFromLocal is called with valid location, then area is returned`() =
        runBlockingTest {
            assertEquals(successResponse, repo.getWeatherInfoByCityFromLocal(validLocation))
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `when getWeatherInfoByCityFromLocal is called with invalid location, then error is returned`() =
        runBlockingTest {
            assertEquals(nullException, repo.getWeatherInfoByCityFromLocal(invalidLocation))
        }
}