package com.mivashko.movieland.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class BadRequest extends RuntimeException {

    public BadRequest(String message, Throwable cause) {
        super(message, cause);
    }
}