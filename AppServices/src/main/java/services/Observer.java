package services;


import model.Utilizator;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Observer extends Remote {
    void primesteRezultateRunda(List<String> pctj,List<String> pozLib) throws RemoteException;
    void primesteConcurentii(List<Utilizator> users) throws RemoteException;
    Utilizator getUser() throws RemoteException;
    void primesteClasament(List<String> clasament) throws RemoteException;
}
