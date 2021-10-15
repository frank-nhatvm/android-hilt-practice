package com.frank.practicehilt.data.services

import com.frank.practicehilt.data.database.question.QuestionDao
import com.frank.practicehilt.data.database.question.QuestionEntity
import javax.inject.Inject

open class QuestionLocalService @Inject constructor(private val questionDao: QuestionDao){


    suspend fun deleteAllQuestion(){
        questionDao.deleteAll()
    }

    suspend fun getAllQuestion(): List<QuestionEntity>{
        return questionDao.getAll()
    }

    suspend fun saveListQuestion(questions: List<QuestionEntity>){
        questionDao.insertAll(questions)
    }


}