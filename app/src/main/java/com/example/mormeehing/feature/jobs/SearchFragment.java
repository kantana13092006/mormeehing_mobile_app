package com.example.mormeehing.feature.jobs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mormeehing.R;
import com.google.android.material.chip.Chip;

import java.util.List;

public class SearchFragment extends Fragment {

    private JobListingFilter.Option selectedCategory = JobListingFilter.Option.ALL;
    private JobListingFilter.Option selectedCriterion = JobListingFilter.Option.ALL;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        selectedCategory = JobListingFilter.Option.ALL;
        selectedCriterion = JobListingFilter.Option.ALL;

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        List<JobListing> allListings = ExampleJobListings.create();

        RecyclerView jobList = view.findViewById(R.id.job_list);
        jobList.setLayoutManager(new LinearLayoutManager(requireContext()));
        JobListingAdapter adapter = new JobListingAdapter(allListings);
        jobList.setAdapter(adapter);

        bindFilter(view, R.id.filter_all, allListings, adapter, JobListingFilter.Option.ALL, true);
        bindFilter(view, R.id.filter_food, allListings, adapter, JobListingFilter.Option.FOOD, true);
        bindFilter(view, R.id.filter_retail, allListings, adapter, JobListingFilter.Option.RETAIL, true);
        bindFilter(view, R.id.filter_distance, allListings, adapter, JobListingFilter.Option.DISTANCE, false);
        bindFilter(view, R.id.filter_pay, allListings, adapter, JobListingFilter.Option.PAY, false);
        bindFilter(view, R.id.filter_hours, allListings, adapter, JobListingFilter.Option.HOURS, false);
        bindFilter(view, R.id.filter_days, allListings, adapter, JobListingFilter.Option.DAYS, false);
        updateChipStates(view);

        return view;
    }

    private void bindFilter(
            View root,
            int viewId,
            List<JobListing> allListings,
            JobListingAdapter adapter,
            JobListingFilter.Option option,
            boolean categoryFilter) {
        Chip chip = (Chip) root.findViewById(viewId);
        chip.setCheckable(true);
        chip.setOnClickListener(
                ignored -> {
                    if (categoryFilter) {
                        selectedCategory = option;
                        if (option == JobListingFilter.Option.ALL) {
                            selectedCriterion = JobListingFilter.Option.ALL;
                        }
                    } else {
                        selectedCriterion = option;
                    }
                    adapter.updateListings(JobListingFilter.apply(
                            allListings,
                            selectedCategory,
                            selectedCriterion));
                    updateChipStates(root);
                });
    }

    private void updateChipStates(View root) {
        setChecked(root, R.id.filter_all, selectedCategory == JobListingFilter.Option.ALL);
        setChecked(root, R.id.filter_food, selectedCategory == JobListingFilter.Option.FOOD);
        setChecked(root, R.id.filter_retail, selectedCategory == JobListingFilter.Option.RETAIL);
        setChecked(root, R.id.filter_distance, selectedCriterion == JobListingFilter.Option.DISTANCE);
        setChecked(root, R.id.filter_pay, selectedCriterion == JobListingFilter.Option.PAY);
        setChecked(root, R.id.filter_hours, selectedCriterion == JobListingFilter.Option.HOURS);
        setChecked(root, R.id.filter_days, selectedCriterion == JobListingFilter.Option.DAYS);
    }

    private void setChecked(View root, int viewId, boolean checked) {
        Chip chip = (Chip) root.findViewById(viewId);
        chip.setChecked(checked);
    }
}
