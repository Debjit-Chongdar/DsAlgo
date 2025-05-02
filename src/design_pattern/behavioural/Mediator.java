package design_pattern.behavioural;

import java.util.ArrayList;
import java.util.List;

interface UserMediator{
    void addUser(String name);
    void sendMessage(String fromUser, String messageBody);
}
class ChatRoom implements UserMediator{
    List<String> users = new ArrayList<>();

    @Override
    public void addUser(String name) {
        users.add(name);
    }

    @Override
    public void sendMessage(String fromUser, String messageBody) {
        users.stream().filter(user -> !user.equals(fromUser))
                .forEach(user -> System.out.println(fromUser+" send "+messageBody+" to "+user));
    }
}

public class Mediator {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.addUser("abc");
        chatRoom.addUser("mnp");
        chatRoom.addUser("xyz");
        chatRoom.sendMessage("mnp", "Hello all");
    }
}
