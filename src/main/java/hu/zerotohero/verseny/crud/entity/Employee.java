package hu.zerotohero.verseny.crud.entity;

import hu.zerotohero.verseny.crud.util.EmployeeJob;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private EmployeeJob job;

    @ManyToOne
    private Location worksAt;

    @OneToOne
    private Equipment operates;

    @Column(nullable = false)
    private Integer salary;

    public Employee() {

    }

    public Employee(String name, EmployeeJob job, Location location, Equipment equipment, Integer salary) {
        this.name = name;
        this.job = job;
        this.worksAt = location;
        this.operates = equipment;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeJob getJob() {
        return job;
    }

    public void setJob(EmployeeJob job) {
        this.job = job;
    }

    public Location getWorksAt() {
        return worksAt;
    }

    public void setWorksAt(Location worksAt) {
        this.worksAt = worksAt;
    }

    public Equipment getOperates() {
        return operates;
    }

    public void setOperates(Equipment operates) {
        this.operates = operates;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
