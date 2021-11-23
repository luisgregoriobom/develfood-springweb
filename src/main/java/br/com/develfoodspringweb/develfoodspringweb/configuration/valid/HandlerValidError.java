package br.com.develfoodspringweb.develfoodspringweb.configuration.valid;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luis Gregorio.
 *
 * In this class we have the intercept of any exception that may occur in the program.
 */
@RestControllerAdvice
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HandlerValidError {

    private final MessageSource messageSource;

    /**
     * Method to intercept the exception and apply a more user-friendly message.
     * @param exception
     * @return
     * @author: Luis Gregorio
     */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<FormErrorDto> handle(MethodArgumentNotValidException exception) {
        List<FormErrorDto> dto = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            FormErrorDto error = new FormErrorDto(e.getField(), message);
            dto.add(error);
        });

        return dto;
    }
}
