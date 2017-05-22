package com.elefirst.base.exception;

/**
 * Created by VM on 5/22/2017.
 */
public class SessionExpiredException extends Exception {
    public SessionExpiredException() {
        super();
    }

    public SessionExpiredException(String message) {
        super(message);
    }

    public SessionExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public SessionExpiredException(Throwable cause) {
        super(cause);
    }

    protected SessionExpiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}