package com.bluetoya.taradiddle.feature.auth.validator;

@FunctionalInterface
public interface Validator<T> {

    void validate(T t);

}
