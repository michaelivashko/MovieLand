package com.mivashko.movieland.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectJsonParsing extends RuntimeException {

    public IncorrectJsonParsing(String message, Throwable cause) {
        super(message, cause);
    }
}