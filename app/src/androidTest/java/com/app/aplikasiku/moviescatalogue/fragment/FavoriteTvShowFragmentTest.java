package com.app.aplikasiku.moviescatalogue.fragment;

import com.app.aplikasiku.moviescatalogue.R;
import com.app.aplikasiku.moviescatalogue.testing.SingleFragmentActivity;
import com.app.aplikasiku.moviescatalogue.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class FavoriteTvShowFragmentTest {

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private FavoriteTvShowFragment listTvFragment = new FavoriteTvShowFragment();

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
        activityRule.getActivity().setFragment(listTvFragment);
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void ToDetailsTv(){
        onView(withId(R.id.rc_fav)).check(matches(isDisplayed()));
        onView(withId(R.id.rc_fav)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.txt_title)).check(matches(isDisplayed()));
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_tanggal)).check(matches(isDisplayed()));
    }
}