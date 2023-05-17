package hbm;

import model.SungJuk;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HelloHBM03 {
    public static void main(String[] args) {
        // jpa 설정 파일 읽어옴
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        // jpa에서 생성한 em을 session 객체로 전환
        Session session = em.unwrap(Session.class);
        SessionFactory sf = session.getSessionFactory();
        Session sess = sf.openSession();


        try{

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            em.close();
            emf.close();
        }
    }
}
