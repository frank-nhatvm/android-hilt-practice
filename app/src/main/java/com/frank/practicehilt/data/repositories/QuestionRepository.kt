package com.frank.practicehilt.data.repositories

import com.frank.practicehilt.data.entities.Question
import com.frank.practicehilt.data.entities.QuestionList
import com.frank.practicehilt.data.entities.toListQuestionEntities
import com.frank.practicehilt.data.entities.toListQuestions
import com.frank.practicehilt.data.services.QuestionLocalService
import com.frank.practicehilt.data.services.QuestionRemoteService
import com.frank.practicehilt.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val questionRemoteService: QuestionRemoteService,
    private val localService: QuestionLocalService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {


    suspend fun getListQuestion(): List<Question> = withContext(dispatcher) {

        val savedQuestion = localService.getAllQuestion()

        if (savedQuestion.isNotEmpty()) {
            savedQuestion.toListQuestions()
        } else {
            getNewAndSave()
        }

    }

     suspend fun getNewAndSave(): List<Question> {
        val questList = questionRemoteService.getListQuestion(currentPage = 1, pageSize = 1)
        val newListQuestion = questList?.items ?: emptyList()

        if (newListQuestion.isNotEmpty()) {
            localService.deleteAllQuestion()
            localService.saveListQuestion(newListQuestion.toListQuestionEntities())
        }

        return newListQuestion
    }



}