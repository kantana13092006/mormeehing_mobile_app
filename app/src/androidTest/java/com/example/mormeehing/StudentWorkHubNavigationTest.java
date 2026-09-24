package com.example.mormeehing;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.NoActivityResumedException;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.fragment.NavHostFragment;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.Visibility.GONE;
import static androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class StudentWorkHubNavigationTest {

    @Test
    public void appStartsOnSplash() {
        try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
            onView(withText(R.string.title_splash)).check(matches(isDisplayed()));
        }
    }

    @Test
    public void getStartedOpensLogin() {
        try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
            onView(withText(R.string.action_get_started)).perform(click());
            onView(withId(R.id.login_title)).check(matches(isDisplayed()));
        }
    }

    @Test
    public void loginButtonOnSplashOpensLogin() {
        try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
            onView(withText(R.string.action_login)).perform(click());
            onView(withId(R.id.login_title)).check(matches(isDisplayed()));
        }
    }

    @Test
    public void mockCredentialsOpenHome() {
        try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
            onView(withText(R.string.action_get_started)).perform(click());
            onView(withId(R.id.input_email)).perform(replaceText("student@example.com"));
            onView(withId(R.id.input_password)).perform(replaceText("password"));
            onView(withId(R.id.submit_login)).perform(click());
            onView(withId(R.id.page_title)).check(matches(withText(R.string.title_home)));
            onView(withId(R.id.nav_home)).check(matches(isDisplayed()));
        }
    }

    @Test
    public void invalidCredentialsStayOnLoginAndShowError() {
        try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
            onView(withText(R.string.action_get_started)).perform(click());
            onView(withId(R.id.input_email)).perform(replaceText("wrong@example.com"));
            onView(withId(R.id.input_password)).perform(replaceText("wrong"));
            onView(withId(R.id.submit_login)).perform(click());
            onView(withId(R.id.login_title)).check(matches(isDisplayed()));
            onView(withText(R.string.error_invalid_credentials)).check(matches(isDisplayed()));
        }
    }

    @Test
    public void backFromHomeFinishesActivityAfterSuccessfulLogin() {
        try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
            onView(withText(R.string.action_get_started)).perform(click());
            onView(withId(R.id.input_email)).perform(replaceText("student@example.com"));
            onView(withId(R.id.input_password)).perform(replaceText("password"));
            onView(withId(R.id.submit_login)).perform(click());
            onView(withId(R.id.page_title)).check(matches(withText(R.string.title_home)));
            try {
                pressBack();
            } catch (NoActivityResumedException expected) {
                // Espresso reports the expected result when Back finishes the only activity.
            }
            assertEquals(Lifecycle.State.DESTROYED, scenario.getState());
        }
    }

    @Test
    public void bottomMenuRoutesToEachMainPage() {
        try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
            loginWithMockCredentials();
            onView(withId(R.id.nav_search)).perform(click());
            onView(withId(R.id.page_title)).check(matches(withText(R.string.title_search)));
            onView(withId(R.id.nav_schedule)).perform(click());
            onView(withId(R.id.page_title)).check(matches(withText(R.string.title_schedule)));
            onView(withId(R.id.nav_profile)).perform(click());
            onView(withId(R.id.page_title)).check(matches(withText(R.string.title_profile)));
            onView(withId(R.id.nav_home)).perform(click());
            onView(withId(R.id.page_title)).check(matches(withText(R.string.title_home)));
        }
    }

    @Test
    public void searchPageShowsExampleJobListings() {
        try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
            loginWithMockCredentials();
            onView(withId(R.id.nav_search)).perform(click());
            onView(withText(R.string.job_cafe_title)).check(matches(isDisplayed()));
            onView(withText(R.string.job_cafe_company)).check(matches(isDisplayed()));
            onView(withText(R.string.job_cafe_pay)).check(matches(isDisplayed()));
            onView(withText(R.string.job_store_title)).check(matches(isDisplayed()));
            onView(withText(R.string.job_event_title)).check(matches(isDisplayed()));
        }
    }

    @Test
    public void centerFabRoutesToCreateJobPage() {
        try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
            loginWithMockCredentials();
            onView(withId(R.id.create_job_fab)).perform(click());
            onView(withId(R.id.page_title)).check(matches(withText(R.string.title_create_job)));
        }
    }

    @Test
    public void secondaryRoutesExposeTitleOnlyPages() {
        try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
            navigateTo(scenario, R.id.jobDetailsFragment);
            onView(withId(R.id.page_title)).check(matches(withText(R.string.title_job_details)));
            onView(withId(R.id.bottom_app_bar)).check(matches(withEffectiveVisibility(GONE)));

            navigateTo(scenario, R.id.workHoursFragment);
            onView(withId(R.id.page_title)).check(matches(withText(R.string.title_work_hours)));
            onView(withId(R.id.bottom_app_bar)).check(matches(withEffectiveVisibility(VISIBLE)));

            navigateTo(scenario, R.id.incomeFragment);
            onView(withId(R.id.page_title)).check(matches(withText(R.string.title_income)));
            navigateTo(scenario, R.id.applicationsFragment);
            onView(withId(R.id.page_title)).check(matches(withText(R.string.title_applications)));
            navigateTo(scenario, R.id.searchFiltersFragment);
            onView(withId(R.id.page_title)).check(matches(withText(R.string.title_search_filters)));
            onView(withId(R.id.bottom_app_bar)).check(matches(withEffectiveVisibility(GONE)));
        }
    }

    private void loginWithMockCredentials() {
        onView(withText(R.string.action_get_started)).perform(click());
        onView(withId(R.id.input_email)).perform(replaceText("student@example.com"));
        onView(withId(R.id.input_password)).perform(replaceText("password"));
        onView(withId(R.id.submit_login)).perform(click());
        onView(withId(R.id.page_title)).check(matches(withText(R.string.title_home)));
    }

    private void navigateTo(ActivityScenario<MainActivity> scenario, int destinationId) {
        scenario.onActivity(activity -> {
            NavHostFragment navHost = (NavHostFragment) activity.getSupportFragmentManager()
                    .findFragmentById(R.id.nav_host);
            if (navHost == null) {
                throw new AssertionError("Navigation host is missing");
            }
            navHost.getNavController().navigate(destinationId);
        });
    }
}
