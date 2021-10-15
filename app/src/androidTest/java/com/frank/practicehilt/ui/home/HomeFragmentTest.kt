package com.frank.practicehilt.ui.home

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.frank.practicehilt.R
import com.frank.practicehilt.launchFragmentInHiltContainer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {


    @Before
    fun setup(){
        launchFragmentInHiltContainer<HomeFragment>(themeResId = R.style.Theme_PracticeHilt){

        }
        //scenario.moveToState(Lifecycle.State.CREATED)
    }

    @Test
    fun testShowButtons(){

        Espresso.onView(ViewMatchers.withId(R.id.btnStackOverFlow)).check(ViewAssertions.matches(
            ViewMatchers.isDisplayed()))

    }


}
