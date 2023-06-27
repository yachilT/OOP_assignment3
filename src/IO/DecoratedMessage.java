package IO;

import java.util.Arrays;
import java.util.Comparator;

public class DecoratedMessage extends Message{
    private static final String LINE_CHAR = "~";
    public DecoratedMessage(String value) {
        super(value);
        decorate();
    }

    public void decorate(){
        int n = Arrays.stream(this.value.split("\n")).map(String::length).max(Comparator.comparingInt(i -> i)).orElse(0);
        this.value = LINE_CHAR.repeat(n) + "\n" + this.value + "\n" + LINE_CHAR.repeat(n);
    }

}
