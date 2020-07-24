package hu.zerotohero.verseny.crud.dto;

import hu.zerotohero.verseny.crud.util.EmployeeJob;
import hu.zerotohero.verseny.crud.exception.EmptyAttributeException;

public class EmployeeDTO {
    private String name;
    private EmployeeJob job;
    private Long worksat;
    private Long operates;


    public EmployeeDTO(String name, EmployeeJob job, Long worksat, Long operates) {
        this.name = name;
        this.job = job;
        this.worksat = worksat;
        this.operates = operates;
    }

    public void validate() throws EmptyAttributeException {
        if (name == null || job == null || name.trim().equals("") || worksat == null) {
            throw new EmptyAttributeException();
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
}
