package com.example.myapplication.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapplication.models.School
import com.example.myapplication.repository.SchoolRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import java.lang.Exception

@ExperimentalCoroutinesApi
class SchoolViewModelTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    private lateinit var viewModel: SchoolViewModel
    private lateinit var repository: SchoolRepository

    @Before
    fun setUp() {
        repository = mock(SchoolRepository::class.java)
        viewModel = SchoolViewModel(repository)
    }

    @After
    fun tearDown() {
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun `test fetchSchools() success`() {
        testScope.runBlockingTest {
            val schoolsList = listOf(School("02M260", "School Name", "Overview"))

            `when`(repository.getSchools()).thenReturn(schoolsList)

            viewModel.fetchSchools()

            verify(repository).getSchools()
        }
    }

    @Test
    fun `test fetchSchools() error`() {
        testScope.runBlockingTest {
            `when`(repository.getSchools()).thenThrow(Exception("Network error"))

            viewModel.fetchSchools()

            verify(repository).getSchools()
        }
    }
}
