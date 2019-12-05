package libs;

import java.util.Objects;

public class Message {
    private int id;
    private String message;
    private String localId;
    private String dateTime;
    private int userTo;
    private int userFrom;

    public Message(String message, String localId, String dateTime, int userTo, int userFrom) {
        this.message = message;
        this.localId = localId;
        this.dateTime = dateTime;
        this.userTo = userTo;
        this.userFrom = userFrom;
    }

    public int getId() {
        return id;
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

    public String getLocalId() {
        return localId;
    }

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message1 = (Message) o;
        return userTo == message1.userTo &&
                userFrom == message1.userFrom &&
                message.equals(message1.message) &&
                localId.equals(message1.localId) &&
                dateTime.equals(message1.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, localId, dateTime, userTo, userFrom);
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", localId='" + localId + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", userTo=" + userTo +
                ", userFrom=" + userFrom +
                '}';
    }
}
