package services;

import model.Utilizator;
import org.graalvm.compiler.nodes.memory.OnHeapMemoryAccess;

import java.util.List;

public interface IService {
    Utilizator login(Utilizator user, Observer client) throws AppException;
    void logout(Observer client);
    void changeObserver(Observer oldObserver, Observer newObserver);
    void trimitePropunere(Observer client,String username,int pozitie);
    void register(Observer client, List<Integer> pozitii) throws AppException;
}
