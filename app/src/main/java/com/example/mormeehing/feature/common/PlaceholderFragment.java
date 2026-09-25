package com.example.mormeehing.feature.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mormeehing.R;
public class PlaceholderFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_placeholder, container, false);
        TextView titleView = view.findViewById(R.id.page_title);
        Bundle arguments = getArguments();
        String title = arguments == null ? null : arguments.getString("title");
        if (title == null) {
            NavDestination destination = NavHostFragment.findNavController(this).getCurrentDestination();
            title = titleForDestination(destination == null ? 0 : destination.getId());
        }
        titleView.setText(title);
        return view;
    }

    private String titleForDestination(int destinationId) {
        if (destinationId == R.id.splashFragment) {
            return getString(R.string.title_splash);
        }
        if (destinationId == R.id.loginFragment) {
            return getString(R.string.title_login);
        }
        if (destinationId == R.id.searchFragment) {
            return getString(R.string.title_search);
        }
        if (destinationId == R.id.createJobFragment) {
            return getString(R.string.title_create_job);
        }
        if (destinationId == R.id.scheduleFragment) {
            return getString(R.string.title_schedule);
        }
        if (destinationId == R.id.profileFragment) {
            return getString(R.string.title_profile);
        }
        if (destinationId == R.id.jobDetailsFragment) {
            return getString(R.string.title_job_details);
        }
        if (destinationId == R.id.workHoursFragment) {
            return getString(R.string.title_work_hours);
        }
        if (destinationId == R.id.incomeFragment) {
            return getString(R.string.title_income);
        }
        if (destinationId == R.id.applicationsFragment) {
            return getString(R.string.title_applications);
        }
        if (destinationId == R.id.searchFiltersFragment) {
            return getString(R.string.title_search_filters);
        }
        return getString(R.string.title_home);
    }
}
