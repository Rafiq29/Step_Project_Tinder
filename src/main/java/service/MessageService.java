package service;

import dao.MessageDAO;
import libs.Message;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public class MessageService {
    private MessageDAO messages;

    public MessageService() {
        messages = new MessageDAO();
    }

    public void write(int sender, int receiver, String message) {
        int lastLocalId = messages.getLastLocalId(sender, receiver);
        messages.add(new Message(receiver, sender, message, lastLocalId + 1, LocalDateTime.now().toString()));
    }

    public List<Message> getMessages(int sender, int receiver) {
        return messages.stream()
                .filter(i -> (i.getUserFrom() == sender && i.getUserTo() == receiver) || (i.getUserFrom() == receiver && i.getUserTo() == sender))
                .collect(Collectors.toList());
    }
}
