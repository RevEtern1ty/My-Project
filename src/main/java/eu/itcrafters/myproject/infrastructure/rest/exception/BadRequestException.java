package eu.itcrafters.myproject.infrastructure.rest.exception;

import eu.itcrafters.myproject.infrastructure.rest.error.ErrorCode;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(ErrorCode code, String key, Object value) {
        super(code.format(key, value));
    }
}
