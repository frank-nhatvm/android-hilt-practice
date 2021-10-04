package com.frank.practicehilt.data.services

import com.frank.practicehilt.data.apis.PostAPI
import com.frank.practicehilt.data.entities.Post
import javax.inject.Inject

class PostRemoteService @Inject constructor(private  val postAPI: PostAPI) {

    suspend fun getPosts(): List<Post>? {

        val response = postAPI.getPosts()

        if(response.isSuccessful){
            return response.body()
        }
        else{

            throw Exception(response.message())
        }
    }


}