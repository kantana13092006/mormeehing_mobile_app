package com.example.mormeehing;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {

    private TextView tvemail;
    private  TextView btnLogout;
    private  TextView tvemaildetail;
    private FirebaseAuth auth;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {

        return inflater.inflate(
                R.layout.fragment_profile,
                container,
                false
        );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvemail = view.findViewById(R.id.tvName);
        btnLogout = view.findViewById(R.id.btnLogout);
        tvemaildetail = view.findViewById(R.id.tvemaildetail);

        auth = FirebaseAuth.getInstance();

        FirebaseUser user = auth.getCurrentUser();

        if(user != null) {
            tvemail.setText(user.getEmail()
            );
        }else {
            tvemail.setText("");
        }


        if (user != null){
            tvemaildetail.setText(user.getEmail()
            );
        }else{
            tvemaildetail.setText("");
        }

        btnLogout.setOnClickListener(v -> {

            auth.signOut();

            NavHostFragment.findNavController(this).navigate(R.id.regisFragment);
        });
    }
}