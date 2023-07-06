package repository;

import model.Pozitie;
import model.Propunere;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;

public class PozitieHbRepo implements PozitieRepo{

    public PozitieHbRepo() {
    }

    @Override
    public int getLastGame() {
        MySessionFactory.initialize();
        try(Session session = MySessionFactory.sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Pozitie poz = session.createQuery("FROM Pozitie  WHERE joc = (SELECT MAX(joc) FROM Pozitie)",Pozitie.class)
                        .setMaxResults(1)
                        .uniqueResult();
                tx.commit();
                if(poz!=null)
                    return poz.getJoc();
                else return 0;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        finally {
            MySessionFactory.close();
        }
        return 0;
    }

    @Override
    public Pozitie findOne(int id) {
        return null;
    }

    @Override
    public Iterable<Pozitie> findAll() {
        return null;
    }

    @Override
    public Pozitie save(Pozitie entity) {
        MySessionFactory.initialize();
        try(Session session = MySessionFactory.sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Serializable id = session.save(entity);
                tx.commit();
                entity.setId((Integer) id);
                return entity;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        finally {
            MySessionFactory.close();
        }
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Pozitie entity) {

    }
}
