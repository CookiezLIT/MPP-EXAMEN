package model;


import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@javax.persistence.Entity
@Table(name = "Pozitii")

public class Pozitie extends Entity implements Serializable {
    private Utilizator jucator;
    private int joc;
    private int pozitie;

    public Pozitie() {
    }

    public Pozitie(Utilizator jucator, int joc, int pozitie) {
        this.jucator = jucator;
        this.joc = joc;
        this.pozitie = pozitie;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jucator")
    public Utilizator getJucator() {
        return jucator;
    }


    public void setJucator(Utilizator jucator) {
        this.jucator = jucator;
    }

    public int getJoc() {
        return joc;
    }

    public void setJoc(int joc) {
        this.joc = joc;
    }

    public int getPozitie() {
        return pozitie;
    }

    public void setPozitie(int pozitie) {
        this.pozitie = pozitie;
    }
}
