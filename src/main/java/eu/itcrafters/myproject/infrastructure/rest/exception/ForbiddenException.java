package eu.itcrafters.myproject.infrastructure.rest.exception;

import lombok.Getter;

@Getter
public class ForbiddenException extends RuntimeException {

    public ForbiddenException(String message) {
        super(message);
    }
}
