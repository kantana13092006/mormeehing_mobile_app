package com.example.mormeehing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.TextView;

import java.util.List;

public class SearchFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        List<JobListing> listings = ExampleJobListings.create();
        TextView summary = view.findViewById(R.id.search_result_summary);
        summary.setText(getString(R.string.search_result_summary, listings.size()));

        RecyclerView jobList = view.findViewById(R.id.job_list);
        jobList.setLayoutManager(new LinearLayoutManager(requireContext()));
        jobList.setAdapter(new JobListingAdapter(listings));
        return view;
    }
}
