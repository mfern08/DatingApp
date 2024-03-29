package com.example.dating_app;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<MainActivity>(MainActivity.class);


    @Test
    public void openSecondActivity(){
        onView(withId(R.id.name)).perform(typeText("Maria Fernandez"));
        onView(withId(R.id.username)).perform(typeText("mfern08"));
        onView(withId(R.id.email)).perform(typeText("maria@gmail.com"));
        onView(withId(R.id.occupation)).perform(typeText("Pre-K Teacher"));
        onView(withId(R.id.info)).perform(typeText("Enjoys walking with dog"));
        onView(withId(R.id.date)).perform(click());
        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(PickerActions.setDate(1990, 9, 30));
    }



    @Test
    public void nameisEmpty(){
        onView(withId(R.id.name)).perform(typeText(""));
        onView(withId(R.id.username)).perform(typeText("mfern08"));
        onView(withId(R.id.email)).perform(typeText("maria@gmail.com"));
        onView(withId(R.id.occupation)).perform(typeText("Pre-K Teacher"));
        onView(withId(R.id.info)).perform(typeText("Enjoys walking with dog"));
        onView(withId(R.id.date)).perform(click());
        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(PickerActions.setDate(1990, 9, 30));
        onView(allOf(withId(R.id.name), hasErrorText("Please Enter Name")));

    }

    @Test
    public void usernameEmpty(){
        onView(withId(R.id.username)).perform(typeText(""));
        onView(withId(R.id.name)).perform(typeText("Maria Fernandez"));
        onView(withId(R.id.email)).perform(typeText("maria@gmail.com"));
        onView(withId(R.id.occupation)).perform(typeText("Pre-K Teacher"));
        onView(withId(R.id.info)).perform(typeText("Enjoys walking with dog"));
        onView(withId(R.id.date)).perform(click());
        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(PickerActions.setDate(1990, 9, 30));
        onView(allOf(withId(R.id.username), hasErrorText("Please Enter Username")));
    }


    @Test
    public void emailValid(){
        onView(withId(R.id.name)).perform(typeText("Maria Fernandez"));
        onView(withId(R.id.username)).perform(typeText("mfern08"));
        onView(withId(R.id.email)).perform(typeText("adfadf"));
        onView(withId(R.id.occupation)).perform(typeText("Pre-K Teacher"));
        onView(withId(R.id.info)).perform(typeText("Enjoys walking with dog"));
        onView(withId(R.id.date)).perform(click());
        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(PickerActions.setDate(1990, 9, 30));
        onView(allOf(withId(R.id.email), hasErrorText("Enter valid Email")));
    }


    @Test
    public void emailNotEmpty(){
        onView(withId(R.id.email)).perform(typeText(""));
        onView(withId(R.id.name)).perform(typeText("Maria Fernandez"));
        onView(withId(R.id.username)).perform(typeText("mfern08"));
        onView(withId(R.id.occupation)).perform(typeText("Pre-K Teacher"));
        onView(withId(R.id.info)).perform(typeText("Enjoys walking with dog"));
        onView(withId(R.id.date)).perform(click());
        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(PickerActions.setDate(1990, 9, 30));
        onView(allOf(withId(R.id.email), hasErrorText("Enter valid Email")));
    }

    @Test
    public void occupationIsEmpty(){
        onView(withId(R.id.occupation)).perform(typeText(""));
        onView(withId(R.id.name)).perform(typeText("Maria Fernandez"));
        onView(withId(R.id.username)).perform(typeText("mfern08"));
        onView(withId(R.id.email)).perform(typeText("maria@gmail.com"));
        onView(withId(R.id.info)).perform(typeText("Enjoys walking with dog"));
        onView(withId(R.id.date)).perform(click());
        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(PickerActions.setDate(1990, 9, 30));
        onView(allOf(withId(R.id.occupation), hasErrorText("Please enter your occupation")));
    }

    @Test
    public void infoIsEmpty(){
        onView(withId(R.id.info)).perform(typeText(""));
        onView(withId(R.id.name)).perform(typeText("Maria Fernandez"));
        onView(withId(R.id.username)).perform(typeText("mfern08"));
        onView(withId(R.id.email)).perform(typeText("maria@gmail.com"));
        onView(withId(R.id.occupation)).perform(typeText("Pre-K Teacher"));
        onView(withId(R.id.date)).perform(click());
        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(PickerActions.setDate(1990, 9, 30));
        onView(allOf(withId(R.id.info), hasErrorText("Please enter something interesting about you")));
    }

    @Test
    public void isAgeValid(){
        onView(withId(R.id.date)).perform(click());
        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(PickerActions.setDate(1990, 9, 30));
    }

    @Test
    public void underAge() {
        onView(withId(R.id.name)).perform(typeText("Maria Fernandez"));
        onView(withId(R.id.username)).perform(typeText("mfern08"));
        onView(withId(R.id.email)).perform(typeText("maria@gmail.com"));
        onView(withId(R.id.occupation)).perform(typeText("Pre-K Teacher"));
        onView(withId(R.id.info)).perform(typeText("Enjoys walking with dog"));
        onView(withId(R.id.date)).perform(click());
        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 9, 30));
        onView(allOf(withId(R.id.date), hasErrorText("Must Be 18+ years")));
    }

    @Test
    public void RotateAndSignIn(){
        onView(withId(R.id.name)).perform(typeText("Maria"));
        onView(withId(R.id.username)).perform(typeText("mfern08"));
        onView(withId(R.id.email)).perform(typeText("maria@gmail.com"));
        onView(withId(R.id.occupation)).perform(typeText("Pre-K Teacher"));
        onView(withId(R.id.info)).perform(typeText("Enjoys walking with dog"));

        TestUtils.rotateScreen(TestUtils.getActivity(activityScenarioRule));

        onView(withId(R.id.name)).check(matches(withText("Maria")));
        onView(withId(R.id.username)).check(matches(withText("mfern08")));
        onView(withId(R.id.email)).check(matches(withText("maria@gmail.com")));
        onView(withId(R.id.occupation)).check(matches(withText("Pre-K Teacher")));
        onView(withId(R.id.info)).check(matches(withText("Enjoys walking with dog")));
    }
}
