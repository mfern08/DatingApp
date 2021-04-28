package com.example.datingapp;

import android.content.Intent;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SecondActivityTest {

    @Rule
    public ActivityScenarioRule<SecondActivity> activityScenarioRule =
                new ActivityScenarioRule<SecondActivity>(SecondActivity.class);

    @Test
    public void setsRightMessageBasedOnIntentExtra() {
        onView(withId(R.id.name))
                .check(matches(withText("ProfileName")));

        onView(withId(R.id.ProfileAge))
                .check(matches(withText("ProfileAge")));
    }
}

