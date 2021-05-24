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
public class SettingsTest {
    @Rule
    public ActivityScenarioRule<SettingsActivity> activityRule =
            new ActivityScenarioRule<>(SettingsActivity.class);

    // Test important title setting Activity
    @Test
    public void importantUIElementsTitle() {
        onView(withText("Settings")).check(matches(isDisplayed()));
    }

    // Test important Name Input setting Activity
    @Test
    public void importantUIElementsNameInput() {
        onView(withId(R.id.name)).check(matches(isDisplayed()));
    }

    // Test important Save Button setting Activity
    @Test
    public void importantUIElementsSaveButton() {
        onView(withText("Save")).check(matches(isDisplayed()));
    }
}
