package com.example.taskmaster;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DetailsPageTest {
    @Rule
    public ActivityScenarioRule<TaskDetailActivity> activityRule =
            new ActivityScenarioRule<>(TaskDetailActivity.class);
    // Test important All Tasks title
    @Test
    public void importantUIElementsTitle() {
        onView(withId(R.id.title)).check(matches(isDisplayed()));
    }
}
