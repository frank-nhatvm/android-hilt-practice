package com.frank.practicehilt.data.database.question

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.frank.practicehilt.data.database.AppDB
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
class QuestionDaoTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDB

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDB::class.java
        ).build()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun getAll_returns_empty_list_when_call_firstTime() = runBlockingTest {
        val result = database.questionDao().getAll()
        Truth.assertThat(result.size).isEqualTo(0)
    }

    @Test
    fun insertAQuestion_getAllQuestions_returns_oneQuestion() = runBlockingTest {
        val question = QuestionEntity(questionId = 1, title = "test")
        database.questionDao().insertAll(listOf(question))

        val result = database.questionDao().getAll()
        val size = result.size
        val savedQuestion = result.first()

        Truth.assertThat(size).isEqualTo(1)
        Truth.assertThat(savedQuestion.title).isEqualTo("test")
    }

    @Test
    fun insertTwoQuestions_getAll_returns_listOfTwoQuestions() = runBlockingTest {
        val question1 = QuestionEntity(questionId = 1, title = "title1")
        val question2 = QuestionEntity(questionId = 2, title = "title2")
        val listNeededSaveQuestions = listOf(question1, question2)
        database.questionDao().insertAll(listNeededSaveQuestions)

        val result = database.questionDao().getAll()
        val size = result.size
        Truth.assertThat(size).isEqualTo(2)

        val savedQuestion1 = result[0]
        Truth.assertThat(savedQuestion1.questionId).isEqualTo(1)
        Truth.assertThat(savedQuestion1.title).isEqualTo("title1")

        val savedQuestion2 = result[1]
        Truth.assertThat(savedQuestion2.questionId).isEqualTo(2)
        Truth.assertThat(savedQuestion2.title).isEqualTo("title2")
    }


}