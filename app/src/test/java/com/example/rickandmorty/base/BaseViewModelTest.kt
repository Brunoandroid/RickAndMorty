package com.example.rickandmorty.base

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@OptIn(ExperimentalCoroutinesApi::class, ObsoleteCoroutinesApi::class)
class BaseViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var app: Application

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        app = Mockito.mock(Application::class.java)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `defaultLaunch toggles loading before and after block`() = runBlockingTest {
        class TestVM(app: Application) : BaseViewModel(app) {
            fun runPublicBlock() {
                defaultLaunch { delay(10) }
            }
        }
        val vm = TestVM(app)
        val observed = mutableListOf<Boolean>()
        val observer = Observer<Boolean> { if (it != null) observed.add(it) }
        vm.loading.observeForever(observer)

        vm.runPublicBlock()

        testDispatcher.advanceUntilIdle()

        assertEquals(listOf(true, false), observed)
        vm.loading.removeObserver(observer)
    }
}
