package com.example.mormeehing.feature.auth;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.mormeehing.R;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {

    private EditText etEmail;
    private  EditText etPassword;
    private TextView btnLogin;
    private TextView txtRegister;
    private FirebaseAuth auth;

    private  static final String CHANNEL_ID = "login_channel";

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState 
    ){
        return  inflater.inflate(
          R.layout.fragment_login,
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
        btnLogin = view.findViewById(R.id.btnLogin);
        txtRegister = view.findViewById(R.id.txtRegister);

        auth = FirebaseAuth.getInstance();
        
        createNotificationChannel();
        requestNotificationPermission();

        btnLogin.setOnClickListener(v -> login());

        txtRegister.setOnClickListener(v -> {

            NavHostFragment.findNavController(this)
                    .navigate(
                            R.id.regisFragment
                    );
                });
    }

    private void login() {
//        String email = etEmail.getText().toString().trim();
//        String password = etPassword.getText().toString().trim();
//
//        if(email.isEmpty() || password.isEmpty()) {
//
//            Toast.makeText(requireContext(),"กรุณากรอก Email และ Passwprd", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        auth.signInWithEmailAndPassword(email,password)
//                .addOnCompleteListener(task -> {
//                    if(task.isSuccessful()) {
//                        Toast.makeText(requireContext(),"Login Successful", Toast.LENGTH_SHORT).show();
//
//                        showNotification(email);
//
                        NavHostFragment.findNavController(this)
                                .navigate(
                                        R.id.action_login_to_home
                                );
//
//                    }else {
//
//                        Toast.makeText(requireContext(),"Login Failed",Toast.LENGTH_SHORT).show();
//                    }
//                });
    }

    private void createNotificationChannel() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel =
                    new NotificationChannel(
                        CHANNEL_ID,
                            "Login Notification",
                            NotificationManager.IMPORTANCE_HIGH
                    );
            NotificationManager manager =
                    (NotificationManager) requireContext()
                            .getSystemService(
                                    Context.NOTIFICATION_SERVICE
                            );
            if(manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
    }
    private void requestNotificationPermission() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            if(ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(
                        new String[]{
                                Manifest.permission.POST_NOTIFICATIONS
                        },
                        100
                );
            }
        }
    }
    private void showNotification(String email) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            if(ContextCompat.checkSelfPermission(
             requireContext(),
             Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
        NotificationCompat.Builder notification =
                new NotificationCompat.Builder(
                        requireContext(),
                        CHANNEL_ID
                )
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle("เข้าสู่ระบบสำเร็จ")
                        .setContentText("ยินดีต้อนรับ " + email)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setAutoCancel(true);

        NotificationManagerCompat
                .from(requireContext())
                .notify(1,notification.build()
                );
        }
    }







