package com.example.myapplication.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.models.School
import com.example.myapplication.repository.SchoolRepository
import kotlinx.coroutines.launch

// this is my view model class which will have repository in parameter
class SchoolViewModel(private val repository: SchoolRepository): ViewModel() {

    private val _schools= MutableLiveData<List<School>>()
    val schools: LiveData<List<School>> = _schools

    init {
        fetchSchools()
    }

    private fun fetchSchools(){
        viewModelScope.launch {
            try{
                val schoolsList = repository.getSchools()
                _schools.value = schoolsList
            }
            catch (e: Exception){
                println(e.printStackTrace())
            }
        }
    }
}