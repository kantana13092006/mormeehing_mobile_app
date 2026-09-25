package com.example.mormeehing.feature.auth;

public final class MockAuthValidator {

    public enum ValidationError {
        NONE,
        EMAIL_REQUIRED,
        PASSWORD_REQUIRED,
        INVALID_CREDENTIALS
    }

    private final String expectedEmail;
    private final String expectedPassword;

    public MockAuthValidator(String expectedEmail, String expectedPassword) {
        this.expectedEmail = expectedEmail;
        this.expectedPassword = expectedPassword;
    }

    public ValidationError validate(String email, String password) {
        if (email == null || email.trim().isEmpty()) {
            return ValidationError.EMAIL_REQUIRED;
        }
        if (password == null || password.isEmpty()) {
            return ValidationError.PASSWORD_REQUIRED;
        }
        if (expectedEmail.equals(email.trim()) && expectedPassword.equals(password)) {
            return ValidationError.NONE;
        }
        return ValidationError.INVALID_CREDENTIALS;
    }
}
