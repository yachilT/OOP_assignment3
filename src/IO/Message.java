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
    public boolean equals(Object object){
        if(!(object instanceof Message))
            return false;
        return Objects.equals(((Message) object).value, value);
    }
    @Override
    public String toString() {
        return value;
    }
}
