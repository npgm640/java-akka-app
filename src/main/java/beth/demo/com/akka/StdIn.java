package beth.demo.com.akka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StdIn {

    //written to make it work in Intellij as System.console() is null
    //when run inside the IDE

    public static String readLine() {

        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            return in.readLine();
        } catch (
                IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

