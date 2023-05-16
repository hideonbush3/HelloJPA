package model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Long empid;

    @Column(nullable = false, length = 15, name = "FIRST_NAME")
    private String fname;

    @Column(nullable = false, length = 15, name = "LAST_NAME")
    private String lname;

    @Column(length = 15, name = "EMAIL")
    private String email;

    @Column(nullable = false, length = 15, name = "PHONE_NUMBER")
    private String phone;

    @Column(name = "HIRE_DATE")
    private Date hdate;

    @Column(nullable = false, length = 20, name = "JOB_ID")
    private String jobid;

    @Column(nullable = false, name = "SALARY")
    private Integer sal;

    @Column(precision = 4, scale = 2, name = "COMMISSION_PCT")
    private BigDecimal comm;

    @Column(name = "MANAGER_ID")
    private Integer mgrid;

    @Column(name = "DEPARTMENT_ID")
    private Long deptid;

    // 조인대상 객체를 쓴다
    @ManyToOne  // 테이블 연관관계 다대일
    @JoinColumn(name="department_id")   // 실제 컬럼명이 아니고 department 테이블의 id와 조인
    private Department department;
}
