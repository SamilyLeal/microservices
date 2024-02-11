package com.ssl.accounts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(String resourseName, String field, String fieldValue) {
        super(String.format("%s not found with the given input data %s = %s", resourseName, field, fieldValue));
    }
}
