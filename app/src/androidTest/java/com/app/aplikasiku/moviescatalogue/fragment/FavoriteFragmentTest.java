package com.app.aplikasiku.moviescatalogue.fragment;

import com.app.aplikasiku.moviescatalogue.R;
import com.app.aplikasiku.moviescatalogue.testing.SingleFragmentActivity;
import com.app.aplikasiku.moviescatalogue.utils.EspressoIdlingResource;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class FavoriteFragmentTest {

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private FavoriteFragment favoriteFragment = new FavoriteFragment();

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
        activityRule.getActivity().setFragment(favoriteFragment);
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadFavMovie() {
        onView(withId(R.id.item_movie)).check(matches(isDisplayed()));
        onView(withId(R.id.item_movie)).perform(click());
        onView(withId(R.id.rc_fav)).check(matches(isDisplayed()));
    }

    @Test
    public void loadFavTv(){
        onView(withId(R.id.item_tv)).check(matches(isDisplayed()));
        onView(withId(R.id.item_tv)).perform(click());
        onView(withId(R.id.rc_fav)).check(matches(isDisplayed()));
    }
}