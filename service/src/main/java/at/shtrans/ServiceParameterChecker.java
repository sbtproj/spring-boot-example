package at.shtrans;

import at.shtrans.exception.ServiceException;

import java.util.Objects;

public class ServiceParameterChecker {

    public static void checkParameterNonNull(Object parameter, String parameterName) throws ServiceException {
        try {
            Objects.requireNonNull(parameter, "Parameter with name [" + parameterName + "] cannot be NULL!");
        }catch(NullPointerException e){
          throw new ServiceException(e.getMessage());
        }
    }

}
