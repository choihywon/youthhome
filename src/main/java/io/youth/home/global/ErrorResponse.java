package io.youth.home.global;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;


@Getter
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ValidationError> errors;

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class ValidationError {
        private final String field;
        private final String message;

        public static ValidationError of(final FieldError fieldError) {
            return ValidationError.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
        }
    }

    public ErrorResponse(ErrorCode errorCode) {
        this.code = errorCode.toString();
        this.message = errorCode.getMessage();
    }

    public ErrorResponse(BindException ex, ErrorCode errorCode) {
        List<ErrorResponse.ValidationError> validationErrorList = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ErrorResponse.ValidationError::of)
                .collect(Collectors.toList());

        this.code = errorCode.toString();
        this.message = errorCode.getMessage();
        this.errors = validationErrorList;
    }
}
