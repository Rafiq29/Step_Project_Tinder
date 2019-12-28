package service;

import dao.MessageDAO;
import dao.UserDAO;
import libs.Message;
import libs.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class MessageService {
    private MessageDAO messages;
    private UserDAO users;

    public MessageService() {
        users = new UserDAO();
        messages = new MessageDAO();
    }

    private int getLastLocalId(int sender, int receiver) {
        return messages.stream()
                .filter(i -> (i.getUserFrom() == sender && i.getUserTo() == receiver) || (i.getUserFrom() == receiver && i.getUserTo() == sender))
                .map(Message::getLocalId)
                .max(Integer::compareTo)
                .orElse(0);
    }

    private String convertToSenderMessage(Message message) {
        return "<li class=\"send-msg float-right mb-2\"><p class=\"pt-1 pb-1 pl-2 pr-2 m-0 rounded\">" +
                message.getMessage() +
                "</p><span class=\"receive-msg-time\">" + message.getDateTime() + "</li><br>";
    }

    private String convertToReceiverMessage(Message message, String profileURL) {
        return "<li class=\"receive-msg float-left mb-2\"><div class=\"sender-img\"> <img src=\""
                + profileURL +
                "\" class=\"float-left\"></div><div class=\"receive-msg-desc float-left ml-2\"><p class=\"bg-white m-0 pt-1 pb-1 pl-2 pr-2 rounded\">"
                + message.getMessage() +
                "</p><span class=\"receive-msg-time\">" + message.getDateTime() + "</li>";
    }

    public User getUser(int id) {
        return users.stream().filter(oneUser -> oneUser.getId() == id).findFirst().orElse(new User("", ""));
    }

    public void write(int sender, int receiver, String message) {
        if (!message.isEmpty()) {
            int lastLocalId = getLastLocalId(sender, receiver);
            messages.add(new Message(receiver, sender, message, lastLocalId + 1,
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm MM-dd-yyyy"))));
        }
    }

    public List<Message> getMessages(int sender, int receiver) {
        return messages.stream()
                .filter(i -> (i.getUserFrom() == sender && i.getUserTo() == receiver) || (i.getUserFrom() == receiver && i.getUserTo() == sender))
                .sorted(Comparator.comparingInt(Message::getLocalId))
                .collect(Collectors.toList());
    }

    public List<String> getFormattedMessages(int sender, int receiver) {
        this.messages.read();
        User receiverUser = users.get(receiver);
        List<Message> messages = getMessages(sender, receiver);
        return messages.stream()
                .map(oneMessage -> {
                    if (oneMessage.getUserTo() == receiver && oneMessage.getUserFrom() == sender)
                        return convertToSenderMessage(oneMessage);
                    else
                        return convertToReceiverMessage(oneMessage, receiverUser.getImgURL());
                }).collect(Collectors.toList());
    }
}
