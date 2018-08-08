package com.bean;

public class ErrorResponse extends AbstractResponse {

    public static class Error {
        private String message;

        public Error(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    private Error error;

    public ErrorResponse(String message) {
        this.error = new Error(message);
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
