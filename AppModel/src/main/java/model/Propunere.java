package model;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@javax.persistence.Entity
@Table(name = "Propuneri")
public class Propunere extends Entity implements Serializable {
    private Utilizator jucator;
    private int joc;
    private int runda;
    private Utilizator pentru;
    private int pozitie;

    private String sfat;

    public Propunere() {
    }

    public Propunere(Utilizator jucator, int joc, int runda, Utilizator pentru, int pozitie, String sfat) {
        this.jucator = jucator;
        this.joc = joc;
        this.runda = runda;
        this.pentru = pentru;
        this.pozitie = pozitie;
        this.sfat = sfat;
    }

    @ManyToOne(fetch = FetchType.EAGER)
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

    public int getRunda() {
        return runda;
    }

    public void setRunda(int runda) {
        this.runda = runda;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pentru")
    public Utilizator getPentru() {
        return pentru;
    }

    public void setPentru(Utilizator pentru) {
        this.pentru = pentru;
    }

    public int getPozitie() {
        return pozitie;
    }

    public void setPozitie(int pozitie) {
        this.pozitie = pozitie;
    }

    @Override
    public String toString() {
        return "Propunere{" +
                "jucator=" + jucator +
                ", joc=" + joc +
                ", runda=" + runda +
                ", pentru=" + pentru +
                ", pozitie=" + pozitie +
                '}';
    }

    public String getSfat() {
        return sfat;
    }

    public void setSfat(String sfat) {
        this.sfat = sfat;
    }
}
