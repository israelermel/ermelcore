package br.com.israelermel.ermelcorelib.exceptions;

public class SpCustomException extends Throwable {

    public SpCustomException() {
    }

    public SpCustomException(String message) {
        super(message);
    }

    public SpCustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpCustomException(Throwable cause) {
        super(cause);
    }

    public SpCustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
