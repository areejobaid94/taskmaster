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

public class AllTasksTest {
    @Rule
    public ActivityScenarioRule<AllTasksActivity> activityRule =
            new ActivityScenarioRule<>(AllTasksActivity.class);
    // Test important All Tasks title
    @Test
    public void importantUIElementsAllTasks() {
        onView(withText("All Tasks")).check(matches(isDisplayed()));
    }

    // Test important Empty Mock View
    @Test
    public void importantUIElementsEmptyMockView() {
        onView(withId(R.id.mockView2)).check(matches(isDisplayed()));
    }
}
