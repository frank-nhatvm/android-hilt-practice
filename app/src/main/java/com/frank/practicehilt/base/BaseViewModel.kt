package com.frank.practicehilt.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

open class BaseViewModel : ViewModel() {

    protected val parentJob: Job? = null

    var isLoading = MutableLiveData(false)
    private set

    protected fun registerEventParentJobFinish(){
        parentJob?.invokeOnCompletion {
            isLoading.postValue(false)
        }
    }

}