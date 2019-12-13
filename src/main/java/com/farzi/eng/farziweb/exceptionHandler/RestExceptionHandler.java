package com.farzi.eng.farziweb.exceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(FarziRuntimeException.class)
    @ResponseBody
    public ResponseEntity<FarziError> processSIFRuntimeException(FarziRuntimeException ex) {
        FarziError error = ex.getError();
        LOG.debug("{}", error);
        LOG.debug("Exception: ", ex);
        return ResponseEntity.status(error.getHttpStatus()).contentType(MediaType.APPLICATION_JSON)
                .body(ex.getError());
    }
}
