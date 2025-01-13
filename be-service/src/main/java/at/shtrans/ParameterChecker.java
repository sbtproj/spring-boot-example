package at.shtrans;

import java.util.Objects;

public class ParameterChecker {

    public static void checkParameterNonNull(Object parameter, String parameterName) {
        Objects.requireNonNull(parameter, "Parameter with name [" + parameterName + "] cannot be NULL!");
    }

}
