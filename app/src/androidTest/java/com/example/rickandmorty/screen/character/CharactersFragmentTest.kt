package com.example.rickandmorty.screen.character

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.rickandmorty.R
import com.example.rickandmorty.launchFragmentInHiltContainer
import junit.framework.TestCase
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharactersFragmentTest: TestCase() {

    @Test
    fun testing() {
        launchFragmentInHiltContainer<CharactersFragment>()
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        Thread.sleep(10000L)
    }

}