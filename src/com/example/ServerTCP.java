package com.example;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerTCP {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ArrayList<PrintWriter> outs;
    private ArrayList<Socket> clients;
    private ArrayList<Thread> threads;

    public ServerTCP(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }    

    private void echoToAll(String message) {
        for (PrintWriter _out : outs) {
            _out.println(message);  
        }
    }

    private void handleClient(Socket clientSocket) throws IOException {
        clients.add(clientSocket);
        Logger.logInfo("new client connected -> " + clientSocket.toString());
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out.print(Logger.baseLog() + " enter your name -> "); out.flush();
        String name = in.readLine();
        echoToAll(Logger.baseLog() + " <" + Logger.yellowColor + name + Logger.reset + "> " + Logger.greenColor + "connected to the conversation" + Logger.reset);
        outs.add(out);
        boolean bye = false;
        while (!bye) {
            String message = in.readLine();
            if (!message.equals("bye")) {
                echoToAll(Logger.baseLog() + " <" + Logger.yellowColor + name + Logger.reset + "> " + message);                
                Logger.logInfo("new message from " + name + " -> " + message);

            } else {
                bye = true;
                out.close();
                clientSocket.close();
                in.close();
                Logger.logInfo(name + " closing connection -> " + clientSocket.toString());
                echoToAll(Logger.baseLog() + " <" + Logger.yellowColor + name + Logger.reset + "> " + Logger.redColor + "disconnected from the conversation" + Logger.reset);
            }
        }
    }

    public void start() throws IOException {
        outs = new ArrayList<PrintWriter>();
        clients = new ArrayList<Socket>();
        threads = new ArrayList<Thread>();
        Logger.logInfo("server started... listening on port " + this.serverSocket.getLocalPort());
        while (true) {
            clientSocket = serverSocket.accept();
            Thread handleClientThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        handleClient(clientSocket);
                    } catch (IOException e) {
                        Logger.logError("error starting thread");
                    }

                }
            });
            handleClientThread.start();
            threads.add(handleClientThread);

        }
    }

    public void stop() throws IOException {
        for (PrintWriter _out : outs) {
            _out.close();
        }
        for (Socket client : clients) {
            client.close();
        }
        clientSocket.close();
        serverSocket.close();
    }

}
