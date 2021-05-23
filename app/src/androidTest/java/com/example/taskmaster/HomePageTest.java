package com.example.taskmaster;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class HomePageTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    // Test important title in Main Activity
    @Test
    public void  importantUIElementsHeader() {
        onView(withId(R.id.usernameTasks)).check(matches(isDisplayed()));
    }

    // Test important add task button in Main Activity
    @Test
    public void  importantUIElementsAddTaskButton() {
        onView(withText("ADD TASK")).check(matches(isDisplayed()));
    }

    // Test important all tasks button in Main Activity
    @Test
    public void  importantUIElementsAllTasksButton() {
        onView(withText("ALL TASKS")).check(matches(isDisplayed()));
    }

    // Test important settings button in Main Activity
    @Test
    public void  importantUIElementsSettingsButton() {
        onView(withText("Settings")).check(matches(isDisplayed()));
    }

    // Test important RecycleView in Main Activity
    @Test
    public void  importantUIElementsRecycleView() {
        onView(withId(R.id.rec_id)).check(matches(isDisplayed()));
    }

    // Test important RecycleView click task in Main Activity
    @Test
    public void  importantUIElementsRecycleViewClickItem() {
        onView(withId(R.id.rec_id)).perform(click());

//        Espresso.registerIdlingResources(simpleListFragment.idlingResource);

        onView(withId(R.id.rec_id))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        Intents.init();
        intended(hasComponent(TaskDetailActivity.class.getName()));
        Intents.release();
    }
}
