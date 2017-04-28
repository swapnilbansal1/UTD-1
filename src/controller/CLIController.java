package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanlin on 4/27/17.
 */
public class CLIController {
    public CLIController() {
    }

    public void start() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while (!Node.getInstance().isShutDown()) {
            System.out.print("> ");
            String line = null;

            try {
                line = bufferedReader.readLine();
                if (line.equals("quit") || line.equalsIgnoreCase("exit")) {
                    Node.getInstance().shutDown();
                    bufferedReader.close();
                    continue;
                }
                if (line.equals(""))
                    continue;
                if (line.equalsIgnoreCase("display connection")) {
                    System.out.println(Node.getInstance().printCommunicationThreads());
                    continue;
                }
                if (line.toLowerCase().startsWith("disconnect")) {
                    System.out.println("Disconnecting...");
                    Node.getInstance().disconnectNode(line.split(" "));
                    continue;
                }
                if (line.toLowerCase().startsWith("connect")) {
                    System.out.println("Connecting...");
                    Node.getInstance().connectNode(line.split(" "));
                    continue;
                }
            } catch (IOException e) {
                System.err.println("Error while reading line from console. Leaving network...");
                Node.getInstance().shutDown();
                return;
            }
        }
    }

}
