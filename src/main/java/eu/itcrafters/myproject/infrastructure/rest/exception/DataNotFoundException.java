package eu.itcrafters.myproject.infrastructure.rest.exception;

import lombok.Getter;

@Getter
public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message) {
        super(message);
    }
}
