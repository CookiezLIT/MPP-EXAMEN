package repository;

import model.Solutie;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SolutieHbRepo implements SolutieRepo{

    @Autowired
    public SolutieHbRepo(){

    }

    @Override
    public Solutie findOne(int id) {
        return null;
    }

    @Override
    public Iterable<Solutie> findAll() {
        return null;
    }

    @Override
    public Solutie save(Solutie entity) {
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
    public void update(Solutie entity) {

    }
}
