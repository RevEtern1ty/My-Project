package eu.itcrafters.myproject.infrastructure.rest.exception;

import eu.itcrafters.myproject.infrastructure.rest.error.ErrorCode;
import lombok.Getter;

@Getter
public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(ErrorCode code, String key, Object value) {
        super(code.format(key, value));
    }
}
