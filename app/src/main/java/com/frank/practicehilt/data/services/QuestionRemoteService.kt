package com.frank.practicehilt.data.services

import com.frank.practicehilt.data.apis.QuestionAPI
import com.frank.practicehilt.data.entities.QuestionList
import javax.inject.Inject

open class QuestionRemoteService @Inject constructor(private val questionAPI: QuestionAPI) {

    suspend fun getListQuestion(currentPage: Int, pageSize: Int): QuestionList?{

        val parameters = mutableMapOf<String,String>()
        parameters["site"] = "stackoverflow"
        parameters["pagesize"] = "$pageSize"
        parameters["page"] = "$currentPage"
        val response =  questionAPI.getListQuestions(parameters = parameters)

        if(response.isSuccessful){
            return response.body()
        }
        else{

            throw Exception(response.message())
        }

    }

}