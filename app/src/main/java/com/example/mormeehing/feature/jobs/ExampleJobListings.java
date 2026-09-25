package com.example.mormeehing.feature.jobs;

import com.example.mormeehing.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ExampleJobListings {

    private ExampleJobListings() {
    }

    public static List<JobListing> create() {
        List<JobListing> listings = new ArrayList<>();
        listings.add(new JobListing("พนักงานร้านกาแฟ","aaaa","500 บาท",6,"15.00","เปิดรับสมัคร"));

        listings.add(new JobListing("ผู้ดูเเลระบบ","admin","100 บาท",5 ,"15.00","เปิดรับสมัคร"));
        return Collections.unmodifiableList(listings);
    }
}
