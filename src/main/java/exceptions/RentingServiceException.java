package exceptions;

public class RentingServiceException extends RuntimeException{
    private String code;

    public RentingServiceException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
