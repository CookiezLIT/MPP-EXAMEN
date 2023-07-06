package repository;

import model.Propunere;
import model.Utilizator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UtilizatorHbRepo implements UtilizatorRepo {

    @Autowired
    public UtilizatorHbRepo() {}

    @Override
    public Utilizator findOneByUsername(String username) {
        MySessionFactory.initialize();
        try (Session session = MySessionFactory.sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Utilizator user = session.createQuery("from Utilizator where alias = '" + username + "'", Utilizator.class)
                        .setMaxResults(1)
                        .uniqueResult();
                System.out.println("Cautam utilizatorul cu id " + user.getId());
                tx.commit();
                return user;
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
    public Utilizator findOne(int id) {
        return null;
    }

    @Override
    public Iterable<Utilizator> findAll() {
        MySessionFactory.initialize();
        try(Session session = MySessionFactory.sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<Utilizator> users =
                        session.createQuery("from Utilizator as u order by u.alias asc", Utilizator.class).list();
                System.out.println(users.size() + " utilizatori gasiti:");
                for (Utilizator u : users) {
                    System.out.println(u);
                }
                tx.commit();
                return users;
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
    public Utilizator save(Utilizator entity) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Utilizator entity) {

    }

}
