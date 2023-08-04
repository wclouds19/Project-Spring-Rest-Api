package bootcamp.rest.dto;

import java.util.ArrayList;
import java.util.List;


public class ResponDataDto<T> {
    
    private boolean status;
    private List<String> messages = new ArrayList<>();
    private Object data;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public void setdata(Object data) {
        this.data = data;
    } 

    public boolean isStatus() {
        return status;
    }

    public List<String> getMessages() {
        return messages;
    }
    
    public Object getdata() {
        return data;
    }
}
