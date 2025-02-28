package com.bluetoya.taradiddle.feature.auth.validator;

import java.util.ArrayList;
import java.util.List;

public class ValidatorChain<T> {

    private final List<Validator<T>> validators = new ArrayList<>();

    public void addValidator(Validator<T> validator) {
        validators.add(validator);
    }

    public void validate(T value) {
        for (Validator<T> validator : validators) {
            validator.validate(value);
        }
    }

}
