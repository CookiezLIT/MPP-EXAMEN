package model;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@javax.persistence.Entity
@Table(name = "User")

public class Utilizator extends Entity implements Serializable {
    private String alias;


    public Utilizator(String alias) {
        this.alias = alias;
    }


    public Utilizator() {

    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
