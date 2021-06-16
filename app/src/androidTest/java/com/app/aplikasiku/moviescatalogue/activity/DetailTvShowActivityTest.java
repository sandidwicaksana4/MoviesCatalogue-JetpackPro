package com.app.aplikasiku.moviescatalogue.activity;

import android.content.Context;
import android.content.Intent;

import com.app.aplikasiku.moviescatalogue.R;
import com.app.aplikasiku.moviescatalogue.data.entity.TvShowItem;
import com.app.aplikasiku.moviescatalogue.utils.EspressoIdlingResource;
import com.app.aplikasiku.moviescatalogue.utils.FakeTvShowData;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class DetailTvShowActivityTest {

    private TvShowItem fakeTv = FakeTvShowData.generateTv().get(0);

    @Rule
    public ActivityTestRule<DetailTvShowActivity> activityTestRule = new ActivityTestRule<DetailTvShowActivity>(DetailTvShowActivity.class){
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, DetailTvShowActivity.class);
            result.putExtra(DetailTvShowActivity.EXTRA_TV, fakeTv);
            return result;
        }
    };

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadDetailsTv() {
        onView(withId(R.id.txt_title)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_tanggal)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_status)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_deskripsi)).check(matches(isDisplayed()));
    }

}