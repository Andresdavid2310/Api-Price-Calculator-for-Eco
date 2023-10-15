package com.api.pricesCalculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    public ResourceNotFoundException(String resourceName ) {
        super(String.format("No hay registros de %s en el sistema.", resourceName ));
        this.resourceName = resourceName;
    }
}
