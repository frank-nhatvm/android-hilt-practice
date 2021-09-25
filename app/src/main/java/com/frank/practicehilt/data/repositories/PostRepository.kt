package com.frank.practicehilt.data.repositories

import com.frank.practicehilt.data.entities.Post
import com.frank.practicehilt.data.services.PostRemoteService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepository constructor(
    private val postRemoteService: PostRemoteService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getPost(): List<Post> = withContext(dispatcher) {
        emptyList()
    }

}