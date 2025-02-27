package com.bluetoya.taradiddle.feature.auth;

public interface Validator<T> {

    void validate(T t);

}
