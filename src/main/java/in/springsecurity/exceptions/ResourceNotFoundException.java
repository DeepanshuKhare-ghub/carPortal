package in.springsecurity.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    //  when custom exception thrown along with message
    public ResourceNotFoundException(String message){
        super(message);
    }


    // when custom exception is thrown without message(Default)
    public ResourceNotFoundException(){
        super("Resource Not Found On Server");
    }

}
