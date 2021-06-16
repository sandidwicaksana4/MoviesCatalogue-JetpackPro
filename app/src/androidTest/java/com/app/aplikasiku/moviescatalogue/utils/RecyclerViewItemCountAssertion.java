package com.app.aplikasiku.moviescatalogue.utils;

import android.view.View;

import java.util.Objects;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.core.Is.is;

public class RecyclerViewItemCountAssertion implements ViewAssertion {

    private final int expectedCount;

    public RecyclerViewItemCountAssertion(int expectedCount) {
        this.expectedCount = expectedCount;
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
        }

        RecyclerView recyclerView = (RecyclerView) view;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        assertThat(Objects.requireNonNull(adapter).getItemCount(), is(expectedCount));
    }
}
