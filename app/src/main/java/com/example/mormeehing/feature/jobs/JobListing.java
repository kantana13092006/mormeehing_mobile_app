package com.example.mormeehing.feature.jobs;

public final class JobListing {

    private final String titleResId;
    private final String companyResId;
    private final String payResId;
    private final int distance;
    private final String scheduleResId;
    private final String statusResId;
    private final String imageUrl;
    private final Category category;
    private final int hourlyPay;
    private final int shiftHours;
    private final boolean weekdaySchedule;

    public enum Category {
        FOOD,
        RETAIL,
        OTHER
    }

    public JobListing(
             String titleResId,
             String companyResId,
             String payResId,
             int distance,
             String scheduleResId,
             String statusResId,
             String imageUrl) {
        this(
                titleResId,
                companyResId,
                payResId,
                distance,
                scheduleResId,
                statusResId,
                imageUrl,
                Category.OTHER,
                0,
                0,
                false);
    }

    public JobListing(
            String titleResId,
            String companyResId,
            String payResId,
            int distance,
            String scheduleResId,
            String statusResId,
            String imageUrl,
            Category category,
            int hourlyPay,
            int shiftHours,
            boolean weekdaySchedule) {
        this.titleResId = titleResId;
        this.companyResId = companyResId;
        this.payResId = payResId;
        this.distance = distance;
        this.scheduleResId = scheduleResId;
        this.statusResId = statusResId;
        this.imageUrl = imageUrl;
        this.category = category;
        this.hourlyPay = hourlyPay;
        this.shiftHours = shiftHours;
        this.weekdaySchedule = weekdaySchedule;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public int getHourlyPay() {
        return hourlyPay;
    }

    public int getShiftHours() {
        return shiftHours;
    }

    public boolean isWeekdaySchedule() {
        return weekdaySchedule;
    }
}
