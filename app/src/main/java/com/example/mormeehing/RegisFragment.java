package com.example.mormeehing;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class RegisFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private TextView btncreate;
    private TextView btnBack;
    private TextView etEmail;
    private  TextView etPassword;

    FirebaseAuth auth;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(
                R.layout.fragment_regis,
                container,
                false
        );
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);

        btncreate = view.findViewById(R.id.btncreate);
        btnBack = view.findViewById(R.id.btnBack);

        auth = FirebaseAuth.getInstance();


        btnBack.setOnClickListener(v -> {

            NavHostFragment.findNavController(this)
                    .popBackStack();
        });

        btncreate.setOnClickListener(v -> {

            String email = etEmail.getText().toString().trim();

            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(),"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_SHORT).show();
                return;
            }

            auth.createUserWithEmailAndPassword(
                    email,password
            ).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    Toast.makeText(requireContext(),"Register Successful",Toast.LENGTH_SHORT).show();

                    auth.signOut();

                    NavHostFragment.findNavController(this).popBackStack();

                } else {
                    Toast.makeText(requireContext(), "Register Failed : " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            });

        });
    }
}