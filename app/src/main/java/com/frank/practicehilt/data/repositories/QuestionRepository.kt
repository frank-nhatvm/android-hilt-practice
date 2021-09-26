package com.frank.practicehilt.data.repositories

import com.frank.practicehilt.common.FLog
import com.frank.practicehilt.data.entities.Question
import com.frank.practicehilt.data.entities.QuestionList
import com.frank.practicehilt.data.entities.toListQuestionEntities
import com.frank.practicehilt.data.entities.toListQuestions
import com.frank.practicehilt.data.services.QuestionLocalService
import com.frank.practicehilt.data.services.QuestionRemoteService
import com.frank.practicehilt.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val remoteService: QuestionRemoteService,
    private val localService: QuestionLocalService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getListQuestion(): List<Question>? = withContext(dispatcher) {
        FLog.log("QuestionRepository getListQuestion 01 ${Thread.currentThread().name}")
        val savedQuestion = localService.getAllQuestion()
        FLog.log("QuestionRepository getListQuestion 01 ${Thread.currentThread().name} with size ${savedQuestion.size}")
        if (savedQuestion.isNotEmpty()) {
            savedQuestion.toListQuestions()
        } else {
            getNewAndSave()
        }

    }

    suspend fun refresh(): List<Question> = withContext(dispatcher) {
        getNewAndSave()
    }

    private suspend fun getNewAndSave(): List<Question> {
        FLog.log("QuestionRepository getNewAndSave 01 ${Thread.currentThread().name}")
        val questionList = remoteService.getListQuestion(currentPage = 1, pageSize = 1)
        val newListQuestion = questionList?.items ?: emptyList()
        FLog.log("QuestionRepository getNewAndSave 02 ${Thread.currentThread().name} with size ${newListQuestion.size}")
        if (newListQuestion.isNotEmpty()) {
            localService.deleteAllQuestion()
            localService.saveListQuestion(newListQuestion.toListQuestionEntities())
        }
        return newListQuestion
    }

}