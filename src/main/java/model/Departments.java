package model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "departments")
public class Departments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTMENT_ID")
    private Long deptid;

    @Column(nullable = false, name = "DEPARTMENT_NAME")
    private String dname;

    @Column(name = "MANAGER_ID")
    private String mgrid;

    @Column(nullable = false, name = "LOCATION_ID")
    private Integer locid;

    @OneToMany(mappedBy = "dept")
    private List<Employees> employees;
}
