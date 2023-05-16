package jpa;

import model.Employee;

import javax.persistence.*;
import java.util.ArrayList;
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

//            TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);
//            query.setParameter("jobid", "IT_PROG");
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
//            List<Object[]> items = em.createQuery(jpql).getResultList();
//
//            for (Object[] item : items)
//                System.out.println(item[0] + "/" + item[1] + "/" + item[2] + " / " + item[3]);

            // 부서번호가 60번인 사원들의 이름, 직책, 부서명 조회 : join
            jpql = "select e.fname, e.jobid, d.dname from Employee e join" +
                    " e.department d where e.deptid = 60";
//            items = em.createQuery(jpql).getResultList();
//
//            for(Object[] item : items) System.out.println(item[0] + " / " + item[1] + " / " + item[2]);

            // 부서명이 IT인 사원의 사번과 입사일 조회 : 서브쿼리
            jpql = "select e.empid, e.hdate from Employee e where deptid =  " +
            "(select d.deptid from Department d where dname = 'IT')";

//            List<Object[]> items = em.createQuery(jpql).getResultList();
//
//            for(Object[] item : items) System.out.println(item[0] + " / "  + item[1]);

            // 이름, 직책, 연봉이 주어졌을때 사원의 모든 정보 조회 - 동적쿼리
            // 직책이 IT_PROG 인 사원 조회
            // 연봉이 10000이상인 사원 조회
            // 직책이 IT_PROG이고 연봉이 6000 이상인 사원 조회
            String fname = null;
            String jobid = "IT_PROG";
            Integer sal = 6000;    // null 체크를 위해 기본형이 아닌 클래스형으로 선언

            jpql = "select e from Employee e";
            List<String> cndtns = new ArrayList<>(); // 조건절 저장할 배열

            if(fname != null)    cndtns.add(" fname like concat('%', :fname, '%') ");
            if(jobid != null)    cndtns.add(" jobid = :jobid ");
            if(sal != null)      cndtns.add(" sal >= :sal ");

            // 조건식이 하나라도 존재한다면
            if(!cndtns.isEmpty()) jpql += " where " + String.join(" and ", cndtns);

            TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);

            if(fname != null)    query.setParameter("fname", fname);
            if(jobid != null)    query.setParameter("jobid", jobid);
            if(sal != null)      query.setParameter("sal", sal);

            List<Employee> emps = query.getResultList();

            for(Employee emp : emps) System.out.println(emp);

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            em.close();
            emf.close();
        }
    }
}
