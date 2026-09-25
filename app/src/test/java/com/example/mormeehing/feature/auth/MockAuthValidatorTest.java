package com.example.mormeehing.feature.auth;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MockAuthValidatorTest {

    @Test
    public void acceptsExactMockCredentials() {
        MockAuthValidator validator = new MockAuthValidator("student@example.com", "password");

        assertEquals(MockAuthValidator.ValidationError.NONE,
                validator.validate("student@example.com", "password"));
    }

    @Test
    public void rejectsBlankEmailBeforeCredentialComparison() {
        MockAuthValidator validator = new MockAuthValidator("student@example.com", "password");

        assertEquals(MockAuthValidator.ValidationError.EMAIL_REQUIRED,
                validator.validate("  ", "password"));
    }

    @Test
    public void rejectsBlankPasswordBeforeCredentialComparison() {
        MockAuthValidator validator = new MockAuthValidator("student@example.com", "password");

        assertEquals(MockAuthValidator.ValidationError.PASSWORD_REQUIRED,
                validator.validate("student@example.com", ""));
    }

    @Test
    public void rejectsWrongCredentials() {
        MockAuthValidator validator = new MockAuthValidator("student@example.com", "password");

        assertEquals(MockAuthValidator.ValidationError.INVALID_CREDENTIALS,
                validator.validate("student@example.com", "wrong"));
    }
}
