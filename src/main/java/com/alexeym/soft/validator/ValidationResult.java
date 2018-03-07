package com.alexeym.soft.validator;

/**
 * Created by Alexey Matveev on 3/7/2018.
 */
public class ValidationResult {

    private boolean valid;

    private String error;

    public ValidationResult(boolean valid, String error) {
        this.valid = valid;
        this.error = error;
    }

    public static ValidationResult invalid(String error) {
        return new ValidationResult(false, error);
    }

    public static ValidationResult valid() {
        return new ValidationResult(true, null);
    }

    public boolean isValid() {
        return valid;
    }

    public String getError() {
        return error;
    }
}
