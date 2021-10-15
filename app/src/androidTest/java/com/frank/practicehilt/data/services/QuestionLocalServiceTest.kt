package com.frank.practicehilt.data.services

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.frank.practicehilt.data.database.AppDB
import com.frank.practicehilt.data.database.question.QuestionEntity
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class QuestionLocalServiceTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDB

    private lateinit var questionLocalService: QuestionLocalService

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDB::class.java
        ).allowMainThreadQueries().build()

        questionLocalService = QuestionLocalService(database.questionDao())

    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun saveListQuestion_OneElement_getAllQuestion_returns_OneElement() = runBlockingTest {
        val question = QuestionEntity(questionId = 1, title = "test")
        questionLocalService.saveListQuestion(listOf(question))

        val savedList = questionLocalService.getAllQuestion()
        val size = savedList.size
        Truth.assertThat(size).isEqualTo(1)

        val savedQuestion = savedList[0]
        Truth.assertThat(savedQuestion.title).isEqualTo("test")
        Truth.assertThat(savedQuestion.questionId).isEqualTo(1)

    }


}