package repository;

import model.Pozitie;
import model.Utilizator;

public interface PozitieRepo extends Repository<Pozitie>{
    int getLastGame();
}
