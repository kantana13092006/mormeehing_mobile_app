package com.example.mormeehing.feature.jobs;

import java.util.ArrayList;
import java.util.List;

public final class JobListingFilter {

    private JobListingFilter() {
    }

    public enum Option {
        ALL,
        FOOD,
        RETAIL,
        DISTANCE,
        PAY,
        HOURS,
        DAYS
    }

    public static List<JobListing> apply(List<JobListing> listings, Option option) {
        if (option == Option.FOOD || option == Option.RETAIL || option == Option.ALL) {
            return apply(listings, option, Option.ALL);
        }
        return apply(listings, Option.ALL, option);
    }

    public static List<JobListing> apply(
            List<JobListing> listings,
            Option category,
            Option criterion) {
        List<JobListing> filtered = new ArrayList<>();

        for (JobListing listing : listings) {
            if (matchesCategory(listing, category) && matchesCriterion(listing, criterion)) {
                filtered.add(listing);
            }
        }

        return filtered;
    }

    private static boolean matchesCategory(JobListing listing, Option option) {
        switch (option) {
            case FOOD:
                return listing.getCategory() == JobListing.Category.FOOD;
            case RETAIL:
                return listing.getCategory() == JobListing.Category.RETAIL;
            case ALL:
            default:
                return true;
        }
    }

    private static boolean matchesCriterion(JobListing listing, Option option) {
        switch (option) {
            case DISTANCE:
                return listing.getDistance() <= 5;
            case PAY:
                return listing.getHourlyPay() >= 120;
            case HOURS:
                return listing.getShiftHours() <= 5;
            case DAYS:
                return listing.isWeekdaySchedule();
            case ALL:
            default:
                return true;
        }
    }
}
