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
public class AddTaskTest {
    @Rule
    public ActivityScenarioRule<AddTaskActivity> activityRule =
            new ActivityScenarioRule<>(AddTaskActivity.class);

    // Test Important AddTaskButton in Add Task Activity
    @Test
    public void importantUIElementsAddTaskButton() {
        onView(withId(R.id.addTask_add)).check(matches(isDisplayed()));
    }

    // Test Important Title Text input in Add Task Activity
    @Test
    public void importantUIElementsTitle() {
        onView(withId(R.id.title)).check(matches(isDisplayed()));
    }

    // Test Important Body Text input in Add Task Activity
    @Test
    public void importantUIElementsBody() {
        onView(withId(R.id.body)).check(matches(isDisplayed()));
    }

    // Test Important Status Selector in Add Task Activity
    @Test
    public void importantUIElementsStatus() {
        onView(withId(R.id.spinner)).check(matches(isDisplayed()));
    }

    // Test Important Submitted Text View in Add Task Activity
    @Test
    public void importantUIElementsSubmitted() {
        onView(withId(R.id.submitted)).check(matches(isDisplayed()));
    }
}
