package com.example.mormeehing.feature.jobs;

public final class JobListing {

    private final String titleResId;
    private final String companyResId;
    private final String payResId;
    private final int distance;
    private final String scheduleResId;
    private final String statusResId;

    public JobListing(
             String titleResId,
             String companyResId,
             String payResId,
             int distance,
             String scheduleResId,
             String statusResId) {
        this.titleResId = titleResId;
        this.companyResId = companyResId;
        this.payResId = payResId;
        this.distance = distance;
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

    public int getDistance() {
        return distance;
    }

    public String getScheduleResId() {
        return scheduleResId;
    }

    public String getStatusResId() { return statusResId;
    }
}
