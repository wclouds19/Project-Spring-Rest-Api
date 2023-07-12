package bootcamp.rest.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponData<T> {
    
    private boolean status;
    private List<String> messages = new ArrayList<>();
    private T data;
    
    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public void setdata(T data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public List<String> getMessages() {
        return messages;
    }
    
    public T getdata() {
        return data;
    }
    
}
