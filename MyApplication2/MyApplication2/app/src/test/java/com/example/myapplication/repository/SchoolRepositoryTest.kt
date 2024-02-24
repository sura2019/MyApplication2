package com.example.myapplication.repository

import com.example.myapplication.models.School
import com.example.myapplication.network.APIService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class SchoolRepositoryTest {

    @Test
    fun `test getSchools() success`() = runBlocking {
        val mockApiService = mock(APIService::class.java)
        val expectedSchools = listOf(
            School("02M260", "School 1", "Overview 1"),
            School("03M330", "School 2", "Overview 2")
        )
        `when`(mockApiService.getSchools()).thenReturn(expectedSchools)

        val repository = SchoolRepository(mockApiService)
        val actualSchools = repository.getSchools()

        assertEquals(expectedSchools, actualSchools)
    }

    @Test(expected = Exception::class)
    fun `test getSchools() error`() = runBlocking {
        val mockApiService = mock(APIService::class.java)
        `when`(mockApiService.getSchools()).thenThrow(Exception("Network error"))

        val repository = SchoolRepository(mockApiService)
        repository.getSchools()
    }
}
