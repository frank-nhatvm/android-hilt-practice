package com.frank.practicehilt.data.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.frank.practicehilt.MainCoroutineRule
import com.frank.practicehilt.data.services.QuestionLocalService
import com.frank.practicehilt.data.services.QuestionRemoteService
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito


@ExperimentalCoroutinesApi
class QuestionRepositoryTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutinesApi = MainCoroutineRule()

    var remoteService = Mockito.mock(QuestionRemoteService::class.java)

    var localService = Mockito.mock(QuestionLocalService::class.java)

    lateinit var questionRepository: QuestionRepository

    @Before
    fun setup(){
        questionRepository = QuestionRepository(remoteService,localService,Dispatchers.Main)
    }

    @Test
    fun getListQuestion_firstTime_callToRemoteService_toGetData() = mainCoroutinesApi.dispatcher.runBlockingTest{

        Mockito.`when`(localService.getAllQuestion()).thenReturn(emptyList())
        questionRepository.getListQuestion()

        Mockito.verify(remoteService).getListQuestion(1,1)

    }

}