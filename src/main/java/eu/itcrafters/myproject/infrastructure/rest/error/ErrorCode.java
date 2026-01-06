package eu.itcrafters.myproject.infrastructure.rest.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    EMPLOYEE_NOT_FOUND("Employee not found: {id}"),
    EMPLOYEE_CODE_EXISTS("Employee code already exists: {code}"),
    EMPLOYEE_EMAIL_EXISTS("Employee email already exists: {email}"),

    TASK_NOT_FOUND("Task not found: {id}"),
    WORKSTATION_NOT_FOUND("Workstation not found: {id}");

    private final String template;

    public String format(String key, Object value) {
        return template.replace("{" + key + "}", String.valueOf(value));
    }
}
