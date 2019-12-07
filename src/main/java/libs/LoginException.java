package libs;

public class LoginException extends Exception {
    public LoginException(String message) {
        super(message);
    }

    public LoginException() {
        super("Login Error");
    }
}
