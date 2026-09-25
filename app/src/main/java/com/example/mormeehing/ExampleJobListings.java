package com.example.mormeehing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ExampleJobListings {

    private ExampleJobListings() {
    }

    public static List<JobListing> create() {
        List<JobListing> listings = new ArrayList<>();
        listings.add(new JobListing(
                R.string.job_cafe_title,
                R.string.job_cafe_company,
                R.string.job_cafe_pay,
                R.string.job_cafe_distance,
                R.string.job_cafe_schedule,
                R.string.job_status_open));
        listings.add(new JobListing(
                R.string.job_store_title,
                R.string.job_store_company,
                R.string.job_store_pay,
                R.string.job_store_distance,
                R.string.job_store_schedule,
                R.string.job_status_open));
        listings.add(new JobListing(
                R.string.job_event_title,
                R.string.job_event_company,
                R.string.job_event_pay,
                R.string.job_event_distance,
                R.string.job_event_schedule,
                R.string.job_status_open));
        return Collections.unmodifiableList(listings);
    }
}
