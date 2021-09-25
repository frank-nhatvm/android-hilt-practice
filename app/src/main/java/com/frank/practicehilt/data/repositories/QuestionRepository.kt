package com.frank.practicehilt.data.repositories

import com.frank.practicehilt.data.entities.Question
import com.frank.practicehilt.data.entities.QuestionList
import com.frank.practicehilt.data.services.QuestionRemoteService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class QuestionRepository constructor(private val questionRemoteService: QuestionRemoteService,
                                     private val dispatcher: CoroutineDispatcher
) {

    suspend fun getListQuestion(): List<Question> = withContext(dispatcher){
        emptyList()
    }

}