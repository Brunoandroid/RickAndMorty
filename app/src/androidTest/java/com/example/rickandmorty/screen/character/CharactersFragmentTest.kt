package com.example.rickandmorty.screen.character

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import com.example.rickandmorty.R
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class CharactersFragmentTest: TestCase() {

    private lateinit var scenario: FragmentScenario<CharactersFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(themeResId = R.style.ThemeOverlay_AppCompat_Dark)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun testing() {
        onView(withId(R.id.tvTitle)).perform(click())
    }

}