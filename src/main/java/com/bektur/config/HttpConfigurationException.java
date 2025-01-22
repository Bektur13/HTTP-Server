package com.bektur.config;

public class HttpConfigurationException extends RuntimeException {
    public HttpConfigurationException() {

    }

    public HttpConfigurationException(String message) {
        super(message);
    }

    public HttpConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpConfigurationException(Throwable cause) {
        super(cause);
    }

    public HttpConfigurationException(String message, Throwable cause, boolean enableSuppression, boolean wriableStackkTrace) {
        super(message, cause, enableSuppression, wriableStackkTrace);
    }
}
