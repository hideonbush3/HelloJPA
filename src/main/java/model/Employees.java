package model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@Entity
@Table(name = "employees")
public class Employees {
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

    @ManyToOne
    @JoinColumn(name="DEPARTMRNT_ID")
    private Departments dept;
}
