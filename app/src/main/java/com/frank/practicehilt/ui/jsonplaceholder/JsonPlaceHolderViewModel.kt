package com.frank.practicehilt.ui.jsonplaceholder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frank.practicehilt.base.BaseViewModel
import com.frank.practicehilt.data.entities.Post

class JsonPlaceHolderViewModel : BaseViewModel() {


    var latestPost = MutableLiveData<Post>()
    protected set

    fun getAllPosts(){

    }


}