package com.frank.practicehilt.data.apis

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class QuestionAPITest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    private lateinit var mockServer: MockWebServer

    private lateinit var questionAPI: QuestionAPI

    @Before
    fun setup() {
        mockServer = MockWebServer()
        mockServer.start()
        val url = mockServer.url("/")
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val moshiConverter = MoshiConverterFactory.create(moshi)

        val retrofit = Retrofit.Builder().addConverterFactory(moshiConverter)
            .baseUrl(url)
            .build()

        questionAPI = retrofit.create(QuestionAPI::class.java)
    }

    @After
    fun stopService() {
        mockServer.shutdown()
    }

    @Test
    fun getListQuestions_success_returns_questionList_with_listOfOneQuestion_and_noHasMore() =
        runBlocking {
            enqueueResponse("getlistquestion-onequestion-nohasmore.json")

            val response = questionAPI.getListQuestions()
            Truth.assertThat(response.isSuccessful).isTrue()

            val dataResponse = response.body()
            Truth.assertThat(dataResponse?.hasMore).isFalse()

            val listQuestions = dataResponse?.items
            Truth.assertThat(listQuestions?.size).isEqualTo(1)

            val question = listQuestions?.firstOrNull()
            Truth.assertThat(question).isNotNull()
            Truth.assertThat(question?.questionId).isEqualTo(69579067)

        }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap(), responseCode: Int = 200) {

        val inputStream = javaClass.classLoader!!
            .getResourceAsStream(fileName)

        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(responseCode)
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockServer.enqueue(
            mockResponse
                .setBody(source.readString(Charsets.UTF_8))
        )
    }


}