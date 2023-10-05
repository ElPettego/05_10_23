package es_1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerRemoteInterface extends Remote {
    boolean send(String m) throws RemoteException;
    // boolean receive() throws RemoteException;


}
