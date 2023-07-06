package model;

import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "Solutie")
public class Solutie extends Entity {

    private int pozitie;

    private String indiciu;

    public Solutie(int pozitie, String indiciu) {
        this.pozitie = pozitie;
        this.indiciu = indiciu;
    }

    public Solutie() {

    }

    public int getPozitie() {
        return pozitie;
    }

    public void setPozitie(int pozitie) {
        this.pozitie = pozitie;
    }

    public String getIndiciu() {
        return indiciu;
    }

    public void setIndiciu(String indiciu) {
        this.indiciu = indiciu;
    }
}
