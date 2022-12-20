package hilworking.unetapplication;

public class Message_Design {
    private String EmailLog;
    private String message;
    private String dateTime;

    public Message_Design() {

    }

    public Message_Design(String emailLog, String message, String dateTime) {
        this.EmailLog = emailLog;
        this.message = message;
        this.dateTime = dateTime;
    }

    public String getEmailLog() {
        return EmailLog;
    }

    public void setEmailLog(String emailLog) {
        this.EmailLog = emailLog;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
