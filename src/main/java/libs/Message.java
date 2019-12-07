package libs;

import java.util.Objects;

public class Message {
    private int id;
    private int userTo;
    private int userFrom;
    private String message;
    private int localId;
    private String dateTime;


    public Message(int userTo, int userFrom, String message, int localId, String dateTime) {
        this.userTo = userTo;
        this.userFrom = userFrom;
        this.message = message;
        this.localId = localId;
        this.dateTime = dateTime;
    }

    public Message(int id, int userTo, int userFrom, String message, int localId, String dateTime) {
        this.userTo = userTo;
        this.userFrom = userFrom;
        this.id = id;
        this.message = message;
        this.localId = localId;
        this.dateTime = dateTime;
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

    public int getLocalId() {
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
        return userTo == message1.getUserTo() &&
                userFrom == message1.getUserFrom() &&
                message.equals(message1.getMessage()) &&
                localId == message1.getLocalId() &&
                dateTime.equals(message1.getDateTime());
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
