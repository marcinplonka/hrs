package pl.com.bottega.hrs.model.commands;

public class CommandInvalidException extends RuntimeException {

    private ValidationErrors validationErrors;

    public CommandInvalidException(ValidationErrors validationErrors) {
        this.validationErrors = validationErrors;
    }

    public CommandInvalidException(String field, String error) {
        this.validationErrors = new ValidationErrors();
        this.validationErrors.add(field, error);

    }


    public ValidationErrors getValidationErrors() {
        return validationErrors;
    }
}
