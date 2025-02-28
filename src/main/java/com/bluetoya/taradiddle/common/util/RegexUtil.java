package com.bluetoya.taradiddle.common.util;

import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RegexUtil {
    private static final Pattern UPPER_CASE_PATTERN = Pattern.compile("[A-Z]");
    private static final Pattern LOWER_CASE_PATTERN = Pattern.compile("[a-z]");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("[0-9]");

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean hasUpperCase(String input) {
        return UPPER_CASE_PATTERN.matcher(input).find();
    }

    public static boolean hasLowerCase(String input) {
        return LOWER_CASE_PATTERN.matcher(input).find();
    }

    public static boolean hasNumber(String input) {
        return NUMBER_PATTERN.matcher(input).find();
    }

    public static boolean isEmailFormat(String input) {
        return EMAIL_PATTERN.matcher(input).matches();
    }

}
