package dsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import model.Employee;
import model.QEmployee;
import model.QSungJuk;
import model.SungJuk;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class HelloQueryDSL03 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            JPAQueryFactory query = new JPAQueryFactory(em);
            QEmployee qemp =  QEmployee.employee;

            // 사원 추가
//            tx.begin();
//            Employee e = new Employee();
//            em.persist(e);
//            tx.commit();

            // 사원 수정
//            tx.begin();
//                query.update(qemp)
//                        .set(qemp.fname, "득열")
//                        .set(qemp.lname, "김")
//                        .set(qemp.sal, 150000)
//                        .set(qemp.jobid, "IT_PROG").
//                        where(qemp.empid.eq(207L)).execute();
//            tx.commit();

            // 사원 삭제
            tx.begin();
                query.delete(qemp).where(qemp.empid.eq(208L)).execute();
            tx.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }

    }
}