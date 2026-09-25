package com.example.mormeehing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public final class JobListingAdapter extends RecyclerView.Adapter<JobListingAdapter.JobListingViewHolder> {

    private final List<JobListing> listings;

    public JobListingAdapter(List<JobListing> listings) {
        this.listings = listings;
    }

    @NonNull
    @Override
    public JobListingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_job_listing, parent, false);
        return new JobListingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobListingViewHolder holder, int position) {
        JobListing listing = listings.get(position);
        holder.title.setText(listing.getTitleResId());
        holder.company.setText(listing.getCompanyResId());
        holder.pay.setText(listing.getPayResId());
        holder.distance.setText(listing.getDistanceResId());
        holder.schedule.setText(listing.getScheduleResId());
        holder.status.setText(listing.getStatusResId());
    }

    @Override
    public int getItemCount() {
        return listings.size();
    }

    static final class JobListingViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView company;
        private final TextView pay;
        private final TextView distance;
        private final TextView schedule;
        private final TextView status;

        JobListingViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.job_title);
            company = itemView.findViewById(R.id.job_company);
            pay = itemView.findViewById(R.id.job_pay);
            distance = itemView.findViewById(R.id.job_distance);
            schedule = itemView.findViewById(R.id.job_schedule);
            status = itemView.findViewById(R.id.job_status);
        }
    }
}
