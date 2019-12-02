package libs;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message1 = (Message) o;
        return userTo == message1.userTo &&
                userFrom == message1.userFrom &&
                message.equals(message1.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, userTo, userFrom);
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
