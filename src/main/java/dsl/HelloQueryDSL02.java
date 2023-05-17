package dsl;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import model.Employee;
import model.QDepartment;
import model.QEmployee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.querydsl.core.Tuple;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class HelloQueryDSL02 {
    public static void main(String[] args) {
        // jpa 객체 초기화 : emf -> em -> etx
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            // 쿼리 객체 준비
            QEmployee qemp = QEmployee.employee;
            QDepartment qdept = QDepartment.department;
            JPAQueryFactory query = new JPAQueryFactory(em);

            // 조회 - 전체 사원
            /* List<Employee> emps = query.selectFrom(qemp).fetch();

            for (Employee e : emps)
                System.out.println(e)*/;

            // 조회 - 일부 사원, 페이징
            /* List<Employee> emps = query.selectFrom(qemp).offset(30).limit(15).fetch();

            for (Employee e : emps)
                System.out.println(e);*/

            // 사원 데이터 조회 - Query : 이름, 부서번호, 입사일
//            List<Tuple> itmes = query.select(
//                    qemp.fname, qemp.deptid, qemp.hdate).from(qemp).fetch();
//
//            for (Tuple item : itmes)
//                System.out.println(item);

            // 정렬 : Orderby, 부서번호 기준
//            query = new JPAQueryFactory(em);
//            List<Employee> emps = query.selectFrom(qemp).orderBy(qemp.deptid.desc()).fetch();

            // 조건검색 : where - 직책인 IT_PROG인 사원 조회,
//            query = new JPAQueryFactory(em);
//            List<Employee> emps = query.selectFrom(qemp).where(qemp.jobid.eq("IT_PROG")).fetch();
//
//            System.out.println(emps);
            // 조건검색 : 연봉이 20000 이상인 사원 조회
//            query = new JPAQueryFactory(em);
//            List<Employee> emps = query.selectFrom(qemp).where(qemp.sal.goe(15000)).fetch();
//
//            System.out.println(emps);

            // 직책 수 조회 1    결과 : [개수]
//            query = new JPAQueryFactory(em);
//            List<Long> cnt = query.select(qemp.jobid.count()).from(qemp).fetch();
//
//            System.out.println(cnt);


            // fetch()는 반환타입이 List
            // fetchCount()는 반환타입이 Long
            // 직책 수 조회 2    결과 : 개수
//            Long cnt2 = query.select(qemp.jobid).from(qemp).fetchCount();
//
//            System.out.println(cnt2);

            // 직책 수 조회 3    결과 : [개수]
//            cnt = query.select(qemp.jobid.countDistinct()).from(qemp).fetch();

//            System.out.println(cnt);

            // 직책 수 조회 4    결과 : 개수
//            cnt2 = query.select(qemp.jobid).distinct().from(qemp).fetchCount();
//
//            System.out.println(cnt2);

            // 그루핑 : 직책별 최대, 최소, 평균연봉, 직책수 조회
//            query = new JPAQueryFactory(em);
//            List<Tuple> grouping = query.select(
//                    qemp.jobid, qemp.sal.max(),
//                            qemp.sal.min(), qemp.sal.avg(), qemp.jobid.count())
//                    .from(qemp).groupBy(qemp.jobid).fetch();
//
//            System.out.println(grouping);

            // 그루핑 : 직책별 최대, 최소, 평균연봉, 직책수 조회
//            StringPath jbcnt = Expressions.stringPath("jbcnt");    // 별칭정의
//
//            grouping = query.select(
//                    qemp.jobid, qemp.sal.max(),
//                            qemp.sal.min(), qemp.sal.avg(), qemp.jobid.count().as("jbcnt")) // 별칭정의 .as(String)
//                    .from(qemp).groupBy(qemp.jobid).orderBy(jbcnt.desc()).fetch();
//
//            System.out.println(grouping);

            // 서브쿼리1 : 평균연봉보다 작게 받는 사원들 조회
//            query = new JPAQueryFactory(em);
//
//            // 서브쿼리
//            JPQLQuery<Double> subqry = JPAExpressions.select(qemp.sal.avg()).from(qemp);
//
//            // 메인쿼리
//            List<Employee> emps = query.selectFrom(qemp).from(qemp).where(qemp.sal.goe(subqry)).fetch();
//
//            System.out.println(emps);

            // 서브쿼리2 : IT 부서에 근무중인 사원들의 이름, 직책, 급여 조회
//            query = new JPAQueryFactory(em);
//
            JPQLQuery<Long> subqry2 = JPAExpressions.select(qdept.deptid).where(qdept.dname.eq("IT")).from(qdept);
//
//            List<Tuple> items = query.select(qemp.fname, qemp.jobid, qemp.sal).from(qemp).where(qemp.deptid.eq(subqry2)).fetch();
//
//            for(Tuple item : items) System.out.println(item);

            // join : 부서번호가 60번인 사원들의 이름, 직책, 부서명 조회
//            query = new JPAQueryFactory(em);
//            List<Tuple> items = query.select(qemp.fname, qemp.jobid, qdept.dname).from(qemp)
//                    .join(qemp.department, qdept)
//                    .where(qemp.deptid.eq(60L))
//                    .fetch();
//
//            for(Tuple item : items) System.out.println(item);

            // 이름, 직책, 연봉이 주어졌을때 사원의 모든 정보 조회 - 동적쿼리
            // 직책이 IT_PROG 인 사원 조회
            // 연봉이 10000이상인 사원 조회
            // 직책이 IT_PROG이고 연봉이 6000 이상인 사원 조회
            String fname = "ste";
            String jobid = null;
            Integer sal = null;

            List<Employee> emps = query
                    .selectFrom(qemp)
                    .where(
                            (fname!=null) ? qemp.fname.contains(fname) : null,
                            StringUtils.isNotEmpty(jobid) ? qemp.jobid.eq(jobid) : null,
                            (sal!=null) ? qemp.sal.gt(sal) : null
                    )
                    .fetch();

            System.out.println(emps);
            // 필터링
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}