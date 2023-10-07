package com.example;
import java.net.InetAddress;

public abstract class Client {
    private int port;
    private Transport transport;
    private InetAddress address;

    public Client(int port, Transport transport, InetAddress address) {
        this.port = port;
        this.transport = transport;
        this.address = address;
    }

    abstract String sendMessage(String message);
    abstract void stopConnection();
    
}
