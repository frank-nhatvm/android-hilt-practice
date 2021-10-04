package com.frank.practicehilt.ui.jsonplaceholder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frank.practicehilt.base.BaseViewModel
import com.frank.practicehilt.data.entities.Post
import com.frank.practicehilt.data.repositories.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JsonPlaceHolderViewModel @Inject constructor(private val postRepository: PostRepository) :
    BaseViewModel() {


    var latestPost = MutableLiveData<Post>()
        protected set

    fun getAllPosts() {
        parentJob = viewModelScope.launch(exceptionHandler) {
            isLoading.postValue(true)
            val posts = postRepository.getPost()
            posts?.firstOrNull()?.let { post ->
                latestPost.postValue(post)
            }
        }
        registerEventParentJobFinish()
    }


}