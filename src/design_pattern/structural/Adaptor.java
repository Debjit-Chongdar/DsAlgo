package design_pattern.structural;

interface NotificationService{
    void sendNotification(String from, String to, String content);
}
class SMSService implements NotificationService{
    @Override
    public void sendNotification(String from, String to, String content) {
        System.out.println("Send SMS");
    }
}
class EmailService{
    public void sendEmail(String fromEmail, String toEmail, String subject, String content){
        System.out.println("Email send from "+fromEmail+" to "+toEmail+" [subject]: \" "+subject+"\" "+content);
    }
}

class ClientApi{
    private NotificationService notificationService;
    public ClientApi(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    public void notifyUser(String from, String to, String content){
        this.notificationService.sendNotification(from, to, content);
    }
}

class NotificationAdapter implements NotificationService{
    private EmailService emailService;
    public NotificationAdapter(EmailService emailService){
        this.emailService = emailService;
    }
    @Override
    public void sendNotification(String from, String to, String content) {
        emailService.sendEmail(from, to, content, content);
    }
}

public class Adaptor {
    public static void main(String[] args) {
        ClientApi clientApi = new ClientApi(new SMSService());
        clientApi.notifyUser("abc", "xyz", "rain fall");
        //
        ClientApi clientApi1 = new ClientApi(new NotificationAdapter(new EmailService()));
        clientApi1.notifyUser("abc", "xyz", "rain fall");
    }
}
