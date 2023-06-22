package IO;

import java.util.Scanner;

public class CLI {
    private MessageCallback m;
     private InputReader r;

    public CLI(){
        m = System.out::println;
        Scanner scanner = new Scanner(System.in);
        r = scanner::nextLine;
    }

    public MessageCallback getMessageCallback() {
        return m;
    }

    public InputReader getInputReader() {
        return r;
    }
}
