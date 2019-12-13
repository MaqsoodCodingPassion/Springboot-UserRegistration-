package com.farzi.eng.farziweb.exceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FarziRuntimeException extends RuntimeException {

    private final List<FarziError> errors;
    private final transient Object obj;

    public FarziRuntimeException(Exception e, FarziError... errors) {
        super(e);
        obj = null;
        this.errors = new ArrayList<>();
        Collections.addAll(this.errors, errors);
    }

    public FarziRuntimeException(FarziError... errors) {
        super(null, null, false, false);
        obj = null;
        this.errors = new ArrayList<>();
        Collections.addAll(this.errors, errors);
    }

    public FarziRuntimeException(Object obj, FarziError... errors) {
        super(null, null, false, false);
        this.obj = obj;
        this.errors = new ArrayList<>();
        Collections.addAll(this.errors, errors);
    }

    public FarziRuntimeException(List<FarziError> errors) {
        super(null, null, false, false);
        obj = null;
        this.errors = errors;
    }

    public Optional<Object> getObj() {
        return Optional.ofNullable(obj);
    }

    public FarziError getError() {
        return errors.get(0);
    }

    public List<FarziError> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "FarziRuntimeException{" +
                "errors=" + errors +
                ", obj=" + obj +
                "} " + super.toString();
    }
}
