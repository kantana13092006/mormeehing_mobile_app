package com.example.mormeehing.feature.jobs;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class JobListingFilterTest {

    @Test
    public void foodFilterReturnsOnlyFoodJobs() {
        List<JobListing> filtered = JobListingFilter.apply(
                ExampleJobListings.create(),
                JobListingFilter.Option.FOOD);

        assertEquals(2, filtered.size());
    }

    @Test
    public void presetFiltersUseExpectedMockThresholds() {
        List<JobListing> listings = ExampleJobListings.create();

        assertEquals(10, JobListingFilter.apply(listings, JobListingFilter.Option.ALL).size());
        assertEquals(2, JobListingFilter.apply(listings, JobListingFilter.Option.RETAIL).size());
        assertEquals(5, JobListingFilter.apply(listings, JobListingFilter.Option.DISTANCE).size());
        assertEquals(5, JobListingFilter.apply(listings, JobListingFilter.Option.PAY).size());
        assertEquals(6, JobListingFilter.apply(listings, JobListingFilter.Option.HOURS).size());
        assertEquals(6, JobListingFilter.apply(listings, JobListingFilter.Option.DAYS).size());
    }

    @Test
    public void categoryAndCriterionFiltersCanBeCombined() {
        List<JobListing> listings = ExampleJobListings.create();

        assertEquals(2, JobListingFilter.apply(
                listings,
                JobListingFilter.Option.FOOD,
                JobListingFilter.Option.DISTANCE).size());
        assertEquals(1, JobListingFilter.apply(
                listings,
                JobListingFilter.Option.RETAIL,
                JobListingFilter.Option.DISTANCE).size());
    }
}
