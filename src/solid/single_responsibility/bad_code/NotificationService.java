package solid.single_responsibility.bad_code;

public class NotificationService {

    public void sendNotification(String msg){
        System.out.println("Send notification to all user");
    }

    public void saveNotificationToDB(String msg){
        System.out.println("save notif to DB by calling jdbc method");
    }
}
