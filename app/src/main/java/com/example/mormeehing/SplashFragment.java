package com.example.mormeehing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class SplashFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        View.OnClickListener openLogin = ignored ->
                NavHostFragment.findNavController(this).navigate(R.id.action_splash_to_login);
        view.findViewById(R.id.action_get_started).setOnClickListener(openLogin);
        view.findViewById(R.id.action_login).setOnClickListener(openLogin);
        return view;
    }
}
