package net;

import core.Message;
import core.MessageType;
import core.Node;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

public class ConnectionInitiator {
    private String address;
    private int port;
    private char label;
    private Socket socket;

    public ConnectionInitiator(String address, int port, char label) {
        this.address = address;
        this.port = port;
        this.label = label;
        this.socket = null;
    }
    public void connect()
    {
        try {
            socket = new Socket(this.address, this.port);
            System.out.print("Connecting to " + this.address +
                    " at port " + this.port + "\n");
            CommunicationThread communicationThread = new CommunicationThread(this.socket, this.label);
            new Thread(communicationThread).start();
            Thread.sleep(500);
            Node.getInstance().getCommunicationThreads().add(communicationThread);
            communicationThread.send(
                    new Message(MessageType.INIT_CONNECTION,
                            LocalDateTime.now(),
                            Node.getInstance().getID()));
        } catch (UnknownHostException e) {
            System.err.println("\nUnknownHostException when opening Socket to address, " + this.address +
                    " at port " + this.port + ": " + e.getMessage());
        } catch (IOException e) {
            System.err.println("\nIOException when opening Socket to address, " + this.address +
                    " at port " + this.port + ": " + e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
