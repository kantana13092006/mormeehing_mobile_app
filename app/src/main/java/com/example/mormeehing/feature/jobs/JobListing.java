package com.example.mormeehing.feature.jobs;

import androidx.annotation.StringRes;

public final class JobListing {

    private final String titleResId;
    private final String companyResId;
    private final String payResId;
    private final int distanceResId;
    private final String scheduleResId;
    private final String statusResId;

    public JobListing(
             String titleResId,
             String companyResId,
             String payResId,
             int distanceResId,
             String scheduleResId,
             String statusResId) {
        this.titleResId = titleResId;
        this.companyResId = companyResId;
        this.payResId = payResId;
        this.distanceResId = distanceResId;
        this.scheduleResId = scheduleResId;
        this.statusResId = statusResId;
    }

    public String getTitleResId() {
        return titleResId;
    }

    public String getCompanyResId() { return companyResId;
    }

    public String getPayResId() {
        return payResId;
    }

    public int getDistanceResId() {
        return distanceResId;
    }

    public String getScheduleResId() {
        return scheduleResId;
    }

    public String getStatusResId() { return statusResId;
    }
}
