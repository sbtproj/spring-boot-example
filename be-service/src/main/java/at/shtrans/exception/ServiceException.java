package at.shtrans.exception;

public class ServiceException extends Exception {

    public ServiceException(){
    }

    public ServiceException(String errorMessage){
        super(errorMessage);
    }

    public ServiceException(String objectName, Long id){
        super("Does not exist " + objectName + " with ID: " + id);
    }
}
