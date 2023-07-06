package repository;

import model.Utilizator;

public interface UtilizatorRepo extends Repository<Utilizator>{
    Utilizator findOneByUsername(String username);
}
