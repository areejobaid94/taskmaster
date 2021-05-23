package com.example.taskmaster;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;
import org.junit.Test;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class TaskDetailTest {
    @Rule
    public ActivityScenarioRule<TaskDetailActivity> activityRule =
            new ActivityScenarioRule<>(TaskDetailActivity.class);

    // Test important Title Text View in Task Detail Activity
    @Test
    public void  importantUIElementsTitle() {
        onView(withId(R.id.title)).check(matches(isDisplayed()));
    }

    // Test important Description Text View in Task Detail Activity
    @Test
    public void  importantUIElementsDescription() {
        onView(withId(R.id.description)).check(matches(isDisplayed()));
    }
}
