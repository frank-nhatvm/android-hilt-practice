package com.frank.practicehilt.ui.stackoverflow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frank.practicehilt.base.BaseViewModel
import com.frank.practicehilt.data.entities.Question

class StackOverFlowViewModel : BaseViewModel() {

    var listQuestions = MutableLiveData<List<Question>>()
        private set

    fun fetchData() {

    }


    fun refresh() {

    }

}