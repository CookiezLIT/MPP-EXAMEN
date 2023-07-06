package repository;

import model.Propunere;
import model.Utilizator;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PropunereHbRepo implements PropunereRepo{

    @Autowired
    public PropunereHbRepo() {
    }

    @Override
    public Propunere findOne(int id) {
        return null;
    }

    @Override
    public Iterable<Propunere> findAll() {
        return null;
    }

    @Override
    public Propunere save(Propunere entity) {
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

    public Iterable<Propunere> getByUser(Utilizator user){
        MySessionFactory.initialize();
        try (Session session = MySessionFactory.sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<Propunere> proposals = session.createQuery("from Propunere ", Propunere.class).list();
//                List<Propunere> proposals = session.createQuery("from Propunere where joc = '" + user + "'", Propunere.class).list();
                ArrayList<Propunere> final_proposals = new ArrayList<Propunere>();

                for (Propunere p: proposals
                     ) {
                    if (p.getJucator().getId() == user.getId()){

                        final_proposals.add(p);
                    }
                }

                tx.commit();
                return final_proposals;
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
    public void update(Propunere entity) {

    }
}
