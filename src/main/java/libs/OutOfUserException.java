package libs;

public class OutOfUserException extends Exception {
    public OutOfUserException() {
    }

    public OutOfUserException(String message) {
        super(message);
    }
}
