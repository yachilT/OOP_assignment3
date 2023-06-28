package IO;

import java.util.Objects;

public class Message {
    protected String value;
    public Message(String value){
        this.value = value;
    }

    public Message(){
        value = "";
    }

    @Override
    public String toString() {
        return value;
    }
}
