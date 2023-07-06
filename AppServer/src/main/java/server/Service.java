package server;


import model.Pozitie;
import model.Propunere;
import model.Utilizator;
import repository.PozitieRepo;
import repository.PropunereRepo;
import services.AppException;
import services.IService;
import services.Observer;
import repository.UtilizatorRepo;

import java.rmi.RemoteException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Service implements IService {
    private UtilizatorRepo utilizatorRepo;
    private PozitieRepo pozitieRepo;
    private PropunereRepo propunereRepo;
    private ConcurrentLinkedQueue<Observer> loggedClients;
    private ConcurrentLinkedQueue<Observer> jucatoriInAstepare;
    private ConcurrentHashMap<Utilizator,List<Integer>> jucatoriPozitii;
    private ConcurrentHashMap<Utilizator,String> jucatoriPozitiiLibere;
    private ConcurrentHashMap<Utilizator,Integer> punctaje;
    private ConcurrentLinkedQueue<Propunere> propuneriPrimite;
    private int joc;
    private int runda;

    public Service(UtilizatorRepo utilizatorRepo,PozitieRepo pozitieRepo,PropunereRepo propunereRepo) {
        this.utilizatorRepo = utilizatorRepo;
        this.pozitieRepo = pozitieRepo;
        this.propunereRepo = propunereRepo;
        loggedClients = new ConcurrentLinkedQueue<>();
        jucatoriInAstepare = new ConcurrentLinkedQueue<>();
        jucatoriPozitii = new ConcurrentHashMap<>();
        jucatoriPozitiiLibere = new ConcurrentHashMap<>();
        propuneriPrimite = new ConcurrentLinkedQueue<>();
        punctaje = new ConcurrentHashMap<>();
    }

    public synchronized Utilizator login(Utilizator user, Observer client) throws AppException {
        Utilizator utilizator=utilizatorRepo.findOneByUsername(user.getAlias());
        if (utilizator!=null){
            loggedClients.add(client);
            return utilizator;
        }
        else
            throw new AppException("Nu exista utilizator cu acest username!");
    }

    @Override
    public void logout(Observer client){
        loggedClients.remove(client);
    }

    @Override
    public void changeObserver(Observer oldObserver, Observer newObserver) {
        loggedClients.remove(oldObserver);
        if(newObserver!=null)
            loggedClients.add(newObserver);
    }

    @Override
    public void trimitePropunere(Observer client,String username, int pozitie) {
        if(propuneriPrimite.size()<3) {
            try {
                Utilizator user = utilizatorRepo.findOneByUsername(username);
                Propunere propunere = new Propunere(client.getUser(),joc,runda,user,pozitie, "mergi la dreapta");
                propuneriPrimite.add(new Propunere(client.getUser(),joc,runda,user,pozitie, "mergi mai sus"));
                propunereRepo.save(propunere);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public void register(Observer client, List<Integer> pozitii) throws AppException {
        if(jucatoriInAstepare.size()<3) {
            jucatoriInAstepare.add(client);
            try {
                jucatoriPozitii.put(client.getUser(),pozitii);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        else
            throw new AppException("Sunt deja 3 jucatori conectati!");
        if(jucatoriInAstepare.size()==3)
            startJoc();
    }

    private void startJoc(){
        joc = pozitieRepo.getLastGame()+1;
        runda = 0;
        for(Observer o : jucatoriInAstepare){
            List<Utilizator> users = new ArrayList<>();
            for(Observer j : jucatoriInAstepare) {
                try {
                    users.add(j.getUser());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            try {
                o.primesteConcurentii(users);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            try {
                List<Integer> pozitii = jucatoriPozitii.get(o.getUser());
                pozitii.forEach(x->{
                    try {
                        pozitieRepo.save(new Pozitie(o.getUser(),joc,x));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            try {
                jucatoriPozitiiLibere.put(o.getUser(),"_________");
                punctaje.put(o.getUser(),0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        runda = 1;
    }

    private void terminaJoc(){

        List<String> clasament = new ArrayList<>();

        for(Observer o:jucatoriInAstepare) {
            try {
                o.primesteClasament(clasament);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        jucatoriInAstepare.clear();
    }
}

