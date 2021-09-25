package com.frank.practicehilt.data.services

import com.frank.practicehilt.data.database.question.QuestionDao
import com.frank.practicehilt.data.database.question.QuestionEntity

class QuestionLocalService constructor(private val questionDao: QuestionDao){


    suspend fun deleteAllQuestion(){

    }

    suspend fun getAllQuestion(): List<QuestionEntity>{
        return questionDao.getAll()
    }

    suspend fun saveListQuestion(questions: List<QuestionEntity>){
        questionDao.insertAll(questions)
    }


}