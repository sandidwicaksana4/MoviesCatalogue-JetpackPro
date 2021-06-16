package com.app.aplikasiku.moviescatalogue.activity;

import com.app.aplikasiku.moviescatalogue.R;
import com.app.aplikasiku.moviescatalogue.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void toDetailsMovieActivityTest() {
        onView(ViewMatchers.withId(R.id.frame_container)).check(matches(isDisplayed()));
        onView(withId(R.id.item_movie)).check(matches(isDisplayed()));
        onView(withId(R.id.item_movie)).perform(click());

        onView(withId(R.id.rc_film)).check(matches(isDisplayed()));
        onView(withId(R.id.rc_film)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_title)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_tanggal)).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void toDetailsTvActivityTest() {
        onView(withId(R.id.frame_container)).check(matches(isDisplayed()));
        onView(withId(R.id.item_tv)).check(matches(isDisplayed()));
        onView(withId(R.id.item_tv)).perform(click());

        onView(withId(R.id.rc_tv)).check(matches(isDisplayed()));
        onView(withId(R.id.rc_tv)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_title)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_tanggal)).check(matches(isDisplayed()));
    }
}
