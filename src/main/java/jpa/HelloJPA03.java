package jpa;

import model.Employee;

import javax.persistence.*;
import java.util.List;

public class HelloJPA03 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");   // persistence-unit의 이름
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            // 사원 데이터 조회 - TypedQuery
            // createQuert(질의문, 리턴될객체종류)
            String jpql = "select e from Employee as e";
            //List<Employees> emps =
            //        em.createQuery(jpql, Employees.class).getResultList();

            //for (Employees emp : emps)
            //    System.out.println(emp);

            // 사원 데이터 조회 - 이름, 부서번호, 입사일
            // 받아오는 타입이 여러 타입일 경우는 List<Object[]> 타입으로 받는다
            // createQuery(질의문)
            jpql = "select fname, deptid, hdate from Employee e";
            //List<Object[]> items = em.createQuery(jpql).getResultList();

            //for (Object[] item : items)
            //    System.out.println(item[0] + "/" + item[1] + "/" + item[2]);

            // 사원 직책 조회 - jobid가 IT_PROG인 사원
            // 파라미터 바인딩 - :파라미터명
            jpql = "select e from Employee e where jobid = :jobid"; // 이름기반
            //jpql = "select e from Employees e where jobid = ?1";   // 위치기반

            TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);
            query.setParameter("jobid", "IT_PROG");
            //query.setParameter(1, "IT_PROG");

            //emps = query.getResultList();

            //for (Employees emp : emps)
            //    System.out.println(emp);

            // 사원 평균 연봉 조회 - TypedQuery
            jpql = "select avg(sal) from Employee e";
            //Double avgsal = em.createQuery(jpql, Double.class).getSingleResult();

            //System.out.println(avgsal);

            // 사원 직책수 조회
            //jpql = "select count(jobid) from Employees e";
            jpql = "select count(distinct jobid) from Employee e";
            //Long cntjob = em.createQuery(jpql, Long.class).getSingleResult();

            //System.out.println(cntjob);

            // 직책으로 정렬후 3번째 페이지 조회 : 페이징   (페이지당 출력건수 : 15)
            // setFirstResult(startpos) : 페이징 시작 위치
            // setMaxResult(getdatacnt) : 조회할 데이터 수
            jpql = "select e from Employee e order by jobid";
            //List<Employees> pemps = em.createQuery(jpql, Employees.class)
            //    .setFirstResult(31).setMaxResults(15).getResultList();

            //for (Employees emp : pemps)
            //    System.out.println(emp);

            // 직책별 평균 연봉, 사원수 조회
            jpql = "select jobid, avg(sal), count(jobid) from Employee e group by jobid";
            //List<Object[]> items = em.createQuery(jpql).getResultList();

            //for (Object[] item : items)
            //    System.out.println(item[0] + "/" + item[1] + "/" + item[2]);

            // 사원이름, 직책, 부서명 조회 : join
            jpql = "select e.fname, e.jobid, e.deptid, d.dname from Employee e " +
                   " inner join e.department d";
            List<Object[]> items = em.createQuery(jpql).getResultList();

            for (Object[] item : items)
                System.out.println(item[0] + "/" + item[1] + "/" + item[2] + " / " + item[3]);

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            em.close();
            emf.close();
        }
    }
}
