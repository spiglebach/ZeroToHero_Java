package hu.zerotohero.verseny.crud.dto;

import hu.zerotohero.verseny.crud.exception.EmployeeNameNotValidException;
import hu.zerotohero.verseny.crud.exception.InsufficientSalaryException;
import hu.zerotohero.verseny.crud.util.EmployeeJob;
import hu.zerotohero.verseny.crud.exception.EmptyAttributeException;

public class EmployeeDTO {
    private String name;
    private EmployeeJob job;
    private Long worksat;
    private Long operates;
    private Integer salary;


    public EmployeeDTO(String name, EmployeeJob job, Long worksat, Long operates, Integer salary) {
        this.name = name;
        this.job = job;
        this.worksat = worksat;
        this.operates = operates;
        this.salary = salary;
    }

    public void validate() throws EmptyAttributeException,
                                InsufficientSalaryException,
                                EmployeeNameNotValidException {
        if (name == null || job == null || name.trim().equals("") || worksat == null || salary == null) {
            throw new EmptyAttributeException();
        }
        String[] nameParts = name.split(" ");
        if (nameParts.length < 2 || nameParts.length > 3) {
            throw new EmployeeNameNotValidException();
        }
        if (salary < 300) {
            throw new InsufficientSalaryException();
        }
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

    public Long getWorksat() {
        return worksat;
    }

    public void setWorksat(Long worksat) {
        this.worksat = worksat;
    }

    public Long getOperates() {
        return operates;
    }

    public void setOperates(Long operates) {
        this.operates = operates;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
