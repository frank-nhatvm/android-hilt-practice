package com.frank.practicehilt.data.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.frank.practicehilt.MainCoroutineRule
import com.frank.practicehilt.data.entities.Post
import com.frank.practicehilt.data.services.PostRemoteService
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class PostRepositoryTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutinesApi = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var postService: PostRemoteService

    lateinit var postRepository: PostRepository

    @Before
    fun init() {
        postService = Mockito.mock(PostRemoteService::class.java)
        postRepository = PostRepository(postService,Dispatchers.Main)
    }

    @Test
    fun getPost_return_list_with_one_post() = mainCoroutinesApi.dispatcher.runBlockingTest{
        val post = Post(userId = 1, id = 1, title = "title", body = "body")
        val list = listOf(post)
        Mockito.`when`(postService.getPosts()).thenReturn(list)
        val result = postRepository.getPost()
        Truth.assertThat(result?.size).isEqualTo(1)
    }

}