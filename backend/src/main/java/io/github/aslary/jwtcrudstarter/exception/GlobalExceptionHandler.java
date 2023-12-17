package io.github.aslary.jwtcrudstarter.exception;

import io.github.aslary.jwtcrudstarter.dto.RestErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<RestErrorDto> handleUserAlreadyExists(UserAlreadyExistsException e) {
        return new ResponseEntity<>(new RestErrorDto(e.getMessage()), HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, Set<String>> response = new TreeMap<>();

        for (var fieldError : ex.getBindingResult().getFieldErrors()) {
            response.merge(
                    fieldError.getField(),
                    new TreeSet<>(Set.of(fieldError.getDefaultMessage())),
                    (existingSet, newValue) -> {
                        existingSet.addAll(newValue);
                        return existingSet;
                    }
            );
        }

        return new ResponseEntity<>(new RestErrorDto(response), headers, status);
    }

}
