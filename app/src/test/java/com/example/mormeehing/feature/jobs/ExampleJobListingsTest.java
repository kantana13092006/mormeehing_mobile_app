package com.example.mormeehing.feature.jobs;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExampleJobListingsTest {

    @Test
    public void createsJobItemsFromDataClass() {
        List<JobListing> listings = ExampleJobListings.create();

        assertEquals(10, listings.size());
        assertEquals("พนักงานร้านกาแฟ", listings.get(0).getTitleResId());
        assertEquals("7-Eleven สาขามหาวิทยาลัย", listings.get(1).getCompanyResId());
        assertEquals("120 บาท/ชม.", listings.get(2).getPayResId());
    }

    @Test
    public void mockListingsIncludeRemoteImageUrls() {
        List<JobListing> listings = ExampleJobListings.create();

        assertTrue(listings.get(0).getImageUrl().startsWith("https://"));
    }
}
