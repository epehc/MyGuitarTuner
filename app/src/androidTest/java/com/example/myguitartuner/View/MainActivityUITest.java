package com.example.myguitartuner.View;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import android.graphics.drawable.Drawable;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.matcher.ViewMatchers;

import com.example.myguitartuner.MainActivity;
import com.example.myguitartuner.R;

import org.junit.Before;
import org.junit.Test;

public class MainActivityUITest {

    private MainActivity mainActivity;

    @Before
    public void setUp() throws Exception {
        ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);
    }

    @Test
    public void testActivityInView() {
        onView(ViewMatchers.withId(R.id.main)).check(matches(isDisplayed()));
    }

    @Test
    public void testElementsAreVisible() {
        //Check Button elements
        onView(withId(R.id.buttonE4)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonB)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonG)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonD)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonA)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonE2)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonStop)).check(matches(isDisplayed()));

        //Check TextView
        onView(withId(R.id.tvStatus)).check(matches(withText("INACTIVE")));
        onView(withId(R.id.tvStatus)).check(matches(isDisplayed()));

        //Check ImageView Elements
        onView(withId(R.id.ivProgress)).check(matches(isDisplayed()));
        onView(withId(R.id.ivBackground)).check(matches(isDisplayed()));
    }

    @Test
    public void testButtonsAreDisabledAfterClicking() {
        //Perform a Click on a button
        onView(withId(R.id.buttonE4)).perform(click());

        //Verify that the other string buttons are disabled
        onView(withId(R.id.buttonB)).check(matches(not(isEnabled())));
        onView(withId(R.id.buttonG)).check(matches(not(isEnabled())));
        onView(withId(R.id.buttonD)).check(matches(not(isEnabled())));
        onView(withId(R.id.buttonA)).check(matches(not(isEnabled())));
        onView(withId(R.id.buttonE2)).check(matches(not(isEnabled())));

        //Verify that the Stop button is still enabled
        onView(withId(R.id.buttonStop)).check(matches(isEnabled()));
    }

    @Test
    public void testButtonsAreEnabledAfterPressingStopButton() {
        //Perform a Click on a button
        onView(withId(R.id.buttonB)).perform(click());
        onView(withId(R.id.tvStatus)).check(matches(withText("ACTIVE")));

        //Perform a Click on the stop Button
        onView(withId(R.id.buttonStop)).perform(click());
        onView(withId(R.id.tvStatus)).check(matches(withText("INACTIVE")));

        //Verify that the other buttons are enabled after pressing stop
        onView(withId(R.id.buttonE4)).check(matches(isEnabled()));
        onView(withId(R.id.buttonG)).check(matches(isEnabled()));
        onView(withId(R.id.buttonD)).check(matches(isEnabled()));
        onView(withId(R.id.buttonA)).check(matches(isEnabled()));
        onView(withId(R.id.buttonE2)).check(matches(isEnabled()));
    }

    @Test
    public void testSetIvProgress() {
        Drawable previousProgressImage = MainActivity.ivProgress.getDrawable();

        MainActivity.setIvProgress(2);

        assertNotEquals(previousProgressImage, MainActivity.ivProgress.getDrawable());

    }

}