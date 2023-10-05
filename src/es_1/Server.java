package es_1;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class Server implements ServerRemoteInterface {
    ArrayList<String> messaggi; // = new ArrayList<>();
    
    public Server() throws RemoteException {
        this.messaggi = new ArrayList<>();
    }

    @Override
    public boolean send(String m) throws RemoteException {
        messaggi.add(m);
        throw new RemoteException();
    }

    public static void main(String[]args) throws RemoteException {
        // args 1 port number 
        Server remote_object = new Server();
        String server_name = "server_echo";
        int port = Integer.parseInt(args[1]);
        UnicastRemoteObject.exportObject(remote_object, port);
        Registry reg = LocateRegistry.createRegistry(port);
        reg.rebind(server_name, reg);
        System.out.println("server ready");
    }
    // @Override
    // public boolean receive() throws RemoteException {
    //     throw new RemoteException();
    // }



}