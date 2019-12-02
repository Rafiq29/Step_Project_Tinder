package libs;

public class Message {
    private String message;
    private int userTo;
    private int userFrom;

    public Message(String message, int userTo, int userFrom) {
        this.message = message;
        this.userTo = userTo;
        this.userFrom = userFrom;
    }

    public String getMessage() {
        return message;
    }

    public int getUserTo() {
        return userTo;
    }

    public int getUserFrom() {
        return userFrom;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", userTo=" + userTo +
                ", userFrom=" + userFrom +
                '}';
    }
}
