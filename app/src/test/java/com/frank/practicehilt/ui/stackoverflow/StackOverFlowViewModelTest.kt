package com.frank.practicehilt.ui.stackoverflow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.frank.practicehilt.MainCoroutineRule
import com.frank.practicehilt.data.entities.Question
import com.frank.practicehilt.data.repositories.QuestionRepository
import com.frank.practicehilt.getOrAwaitValue
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class StackOverFlowViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutinesApi = MainCoroutineRule()

    var questionRepository = Mockito.mock(QuestionRepository::class.java)

    lateinit var stackOverFlowViewModel: StackOverFlowViewModel

    @Before
    fun setup(){
        stackOverFlowViewModel = StackOverFlowViewModel(questionRepository)
    }

    @Test
    fun fetchData_return_list_with_one_question() = mainCoroutinesApi.dispatcher.runBlockingTest{
        val title = "frank"
        val question = Question(questionId = 1, title = title)
        val listOfQuestions = listOf(question)
        Mockito.`when`(questionRepository.getListQuestion()).thenReturn(listOfQuestions)
        stackOverFlowViewModel.fetchData()
        val result = stackOverFlowViewModel.listQuestions.getOrAwaitValue()
        Truth.assertThat(result.size).isEqualTo(1)

    }

}
