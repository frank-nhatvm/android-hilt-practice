package com.frank.practicehilt.data.services

import com.frank.practicehilt.MainCoroutineRule
import com.frank.practicehilt.data.apis.PostAPI
import com.frank.practicehilt.data.entities.Post
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response

@ExperimentalCoroutinesApi
class PostRemoteServiceTest {

    @get:Rule
    var mainCoroutinesApi = MainCoroutineRule()

    private lateinit var postAPI: PostAPI

    private lateinit var postRemoteService: PostRemoteService

    @Before
    fun init() {
        postAPI = Mockito.mock(PostAPI::class.java)
        postRemoteService = PostRemoteService(postAPI)
    }

    @Test
    fun getPost_returnList_with_one_post() = mainCoroutinesApi.dispatcher.runBlockingTest{
        val post = Post(userId = 1, id = 1, title = "title", body = "body")
        val list = listOf(post)
        Mockito.`when`(postAPI.getPosts()).thenReturn(Response.success(list))


        val result = postRemoteService.getPosts()

        Truth.assertThat(result?.size).isEqualTo(1)

    }

}