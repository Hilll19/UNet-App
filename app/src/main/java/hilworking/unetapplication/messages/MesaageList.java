package hilworking.unetapplication.messages;
//
public class MesaageList {

    private String email, name, lastMessage, profilePic;

    private int unseenMessage;

    public MesaageList(String email, String name, String lastMessage, int unseenMessage) {
        this.name = name;
        this.email = email;
        this.lastMessage = lastMessage;
        this.unseenMessage = unseenMessage;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public int getUnseenMessage() {
        return unseenMessage;
    }
}
