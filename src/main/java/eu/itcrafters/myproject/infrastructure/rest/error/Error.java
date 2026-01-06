package eu.itcrafters.myproject.infrastructure.rest.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Error {
    DATA_NOT_FOUND,
    FORBIDDEN,
    VALIDATION_ERROR,
    BAD_REQUEST,
    INTERNAL_ERROR
}
