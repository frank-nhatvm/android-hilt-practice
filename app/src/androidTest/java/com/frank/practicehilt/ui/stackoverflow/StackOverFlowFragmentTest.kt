package com.frank.practicehilt.ui.stackoverflow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.frank.practicehilt.MainCoroutineRule
import com.frank.practicehilt.R
import com.frank.practicehilt.data.database.question.QuestionEntity
import com.frank.practicehilt.data.services.QuestionLocalService
import com.frank.practicehilt.data.services.QuestionRemoteService
import com.frank.practicehilt.di.CoroutinesDispatcherModule
import com.frank.practicehilt.di.DefaultDispatcher
import com.frank.practicehilt.di.IoDispatcher
import com.frank.practicehilt.di.MainDispatcher
import com.frank.practicehilt.launchFragmentInHiltContainer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

 @UninstallModules(CoroutinesDispatcherModule::class)
@ExperimentalCoroutinesApi
@HiltAndroidTest
class StackOverFlowFragmentTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

//    @BindValue @IoDispatcher @JvmField
//    var ioDispatcher = Dispatchers.Main

//    @BindValue
//    @JvmField
//    var questionRepository = Mockito.mock(QuestionRepository::class.java)

    @BindValue
    @JvmField
    var localService = Mockito.mock(QuestionLocalService::class.java)

//    @BindValue
//    @JvmField
//    var remoteService = Mockito.mock(QuestionRemoteService::class.java)


    @Before
    fun setup() {
        hiltRule.inject()
        //viewModel  = StackOverFlowViewModel(questionRepository)
    //    val viewModel = StackOverFlowViewModel(questionRepository)
        launchFragmentInHiltContainer<StackOverFlowFragment> {

        }

    }

    @Test
    fun show_titleOfQuestion_on_tvResult() = mainCoroutineRule.dispatcher.runBlockingTest {
        val title = "frank"
        val question = QuestionEntity(questionId = 1, title = title)
        val listOfQuestions = listOf(question)

        Mockito.`when`(localService.getAllQuestion()).thenReturn(listOfQuestions)

        Espresso.onView(ViewMatchers.withId(R.id.tvResult))
            .check(ViewAssertions.matches(ViewMatchers.withText(title)))

    }



    @Module
    @InstallIn(SingletonComponent::class)
    object TestCoroutinesDispatcherModule {
        @Provides
        @IoDispatcher
        fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.Main

        @Provides
        @MainDispatcher
        fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

        @Provides
        @DefaultDispatcher
        fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Main
    }

}