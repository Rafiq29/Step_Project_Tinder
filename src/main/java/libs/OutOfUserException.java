package libs;

public class OutOfUserException extends RuntimeException {
    public OutOfUserException() {
    }

    public OutOfUserException(String message) {
        super(message);
    }
}
