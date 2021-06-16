package edu.reactive.server.webfilter;

public class WebFilterException extends RuntimeException {
    public WebFilterException(String message) {
        super(message);
    }
    public WebFilterException(String message, Throwable cause) {
        super(message, cause);
    }
}
