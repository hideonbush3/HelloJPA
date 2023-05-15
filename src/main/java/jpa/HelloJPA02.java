package jpa;

import model.SungJuk;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HelloJPA02 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");   // persistence-unit의 이름
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            // 데이터 추가
            SungJuk sj = new SungJuk();
            sj.setName("문동은");
            sj.setKor(100);
            sj.setEng(40);
            sj.setMat(30);
            tx.begin();
                em.persist(sj);
            tx.commit();

            // 데이터 조회
            tx.begin();
                sj = em.find(SungJuk.class, 1);
                System.out.println(sj);
            tx.commit();

            // 데이터 수정   : setXxx(변경값)
            tx.begin();
                sj = em.find(SungJuk.class, 7);
                sj.setName("엄선임");   // 값 변경
                em.persist(sj);
            tx.commit();

            // 데이터 삭제 : remove(대상)
            tx.begin();
                sj = em.find(SungJuk.class, 8); // 삭제할 객체 찾음
                em.remove(sj);   // 대상객체
            tx.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            em.close();
            emf.close();
        }
    }
}
