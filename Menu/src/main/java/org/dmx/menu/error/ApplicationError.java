package org.dmx.menu.error;

public class ApplicationError {

    private String errorMessage;

    public ApplicationError() {}

    public ApplicationError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
