package com.example.mormeehing.feature.jobs;

import androidx.annotation.StringRes;

public final class JobListing {

    private final int titleResId;
    private final int companyResId;
    private final int payResId;
    private final int distanceResId;
    private final int scheduleResId;
    private final int statusResId;

    public JobListing(
            @StringRes int titleResId,
            @StringRes int companyResId,
            @StringRes int payResId,
            @StringRes int distanceResId,
            @StringRes int scheduleResId,
            @StringRes int statusResId) {
        this.titleResId = titleResId;
        this.companyResId = companyResId;
        this.payResId = payResId;
        this.distanceResId = distanceResId;
        this.scheduleResId = scheduleResId;
        this.statusResId = statusResId;
    }

    public int getTitleResId() {
        return titleResId;
    }

    public int getCompanyResId() {
        return companyResId;
    }

    public int getPayResId() {
        return payResId;
    }

    public int getDistanceResId() {
        return distanceResId;
    }

    public int getScheduleResId() {
        return scheduleResId;
    }

    public int getStatusResId() {
        return statusResId;
    }
}
