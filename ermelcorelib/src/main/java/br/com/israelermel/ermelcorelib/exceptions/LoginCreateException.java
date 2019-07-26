package br.com.israelermel.ermelcorelib.exceptions;

public class LoginCreateException extends Throwable {

    public LoginCreateException() {
    }

    public LoginCreateException(String message) {
        super(message);
    }

    public LoginCreateException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginCreateException(Throwable cause) {
        super(cause);
    }

    public LoginCreateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
