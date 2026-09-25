package com.example.mormeehing.feature.jobs;

import com.example.mormeehing.R;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExampleJobListingsTest {

    @Test
    public void createsJobItemsFromDataClass() {
        List<JobListing> listings = ExampleJobListings.create();

        assertEquals(3, listings.size());
        assertEquals(R.string.job_cafe_title, listings.get(0).getTitleResId());
        assertEquals(R.string.job_store_company, listings.get(1).getCompanyResId());
        assertEquals(R.string.job_event_pay, listings.get(2).getPayResId());
    }
}
