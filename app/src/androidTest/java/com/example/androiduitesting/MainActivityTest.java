package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest


public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);
    @Test
    public void testAddCity() {
        // Click the "Add City" button
        onView(withId(R.id.button_add)).perform(click());

        // Type "Edmonton" into the EditText
        onView(withId(R.id.editText_name)).perform( typeText("Edmonton"));

        // Click the "Confirm" button
        onView(withId(R.id.button_confirm)).perform(click());

        // Verify that "Edmonton" is in the list
        onView(withText("Edmonton")).check(matches(isDisplayed()));




    }

    @Test
    public void testClearCity() {
        // Click the "Add City" button
        onView(withId(R.id.button_add)).perform(click());
        // Type "Edmonton" into the EditText
        onView(withId(R.id.editText_name)).perform(typeText("Edmonton"));
        // Click the "Confirm" button
        onView(withId(R.id.button_confirm)).perform(click());
        // Add another city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Vancouver"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click the "Clear" button
        onView(withId(R.id.button_clear)).perform(click());

        // Verify that the list does not contain any cities
        onView(withText("Edmonton")).check(doesNotExist());
        onView(withText("Vancouver")).check(doesNotExist());

    }

    @Test
    public void testListViews() {
        // Add a city to the list
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        onData(is(instanceOf(String.class))).inAdapterView(withId(R.id.city_list)).atPosition(0).check(matches(withText("Edmonton")));
    }

    @Test
    public void testShowActivity() {
        // Add a city to the list
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the list item
        onData(is(instanceOf(String.class))).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        // Verify that the ShowActivity is displayed
        onView(withId(R.id.show)).check(matches(isDisplayed()));

    }

    @Test
    public void testCorrectCity() {
        // Add a city to the list
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the list item
        onData(is(instanceOf(String.class))).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        // Verify that the correct city is displayed in the ShowActivity
        onView(withText("Edmonton")).check(matches(isDisplayed()));

    }

    @Test
    public void testBackButton() {
        // Add a city to the list
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the list item
        onData(is(instanceOf(String.class))).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        // Verify that the ShowActivity is displayed
        onView(withId(R.id.show)).check(matches(isDisplayed()));

        // Click the back button
        onView(withId(R.id.button_back)).perform(click());

        // Verify that the MainActivity is displayed
        onView(withId(R.id.main)).check(matches(isDisplayed()));

    }

    
}
