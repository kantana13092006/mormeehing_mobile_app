package com.example.mormeehing.feature.auth;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mormeehing.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends Fragment {

    private TextInputLayout emailField;
    private TextInputLayout passwordField;
    private TextInputEditText emailInput;
    private TextInputEditText passwordInput;
    private TextView formError;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        emailField = view.findViewById(R.id.email_field);
        passwordField = view.findViewById(R.id.password_field);
        emailInput = view.findViewById(R.id.input_email);
        passwordInput = view.findViewById(R.id.input_password);
        formError = view.findViewById(R.id.form_error);

        TextWatcher clearEmailError = new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                emailField.setError(null);
                clearFormError();
            }
        };
        TextWatcher clearPasswordError = new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                passwordField.setError(null);
                clearFormError();
            }
        };
        emailInput.addTextChangedListener(clearEmailError);
        passwordInput.addTextChangedListener(clearPasswordError);

        MaterialButton submit = view.findViewById(R.id.submit_login);
        submit.setOnClickListener(ignored -> submitLogin());
        return view;
    }

    private void submitLogin() {
        clearFieldErrors();
        clearFormError();

        MockAuthValidator validator = new MockAuthValidator(
                getString(R.string.mock_user_email),
                getString(R.string.mock_user_password));
        MockAuthValidator.ValidationError result = validator.validate(
                textOf(emailInput),
                textOf(passwordInput));

        if (result == MockAuthValidator.ValidationError.EMAIL_REQUIRED) {
            emailField.setError(getString(R.string.error_email_required));
            return;
        }
        if (result == MockAuthValidator.ValidationError.PASSWORD_REQUIRED) {
            passwordField.setError(getString(R.string.error_password_required));
            return;
        }
        if (result == MockAuthValidator.ValidationError.INVALID_CREDENTIALS) {
            formError.setText(getString(R.string.error_invalid_credentials));
            formError.setVisibility(View.VISIBLE);
            return;
        }

        NavHostFragment.findNavController(this).navigate(R.id.action_login_to_home);
    }

    private void clearFieldErrors() {
        emailField.setError(null);
        passwordField.setError(null);
    }

    private void clearFormError() {
        formError.setText(null);
        formError.setVisibility(View.GONE);
    }

    private String textOf(TextInputEditText input) {
        Editable value = input.getText();
        return value == null ? "" : value.toString();
    }

    private abstract static class SimpleTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence sequence, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence sequence, int start, int before, int count) {
        }
    }
}
