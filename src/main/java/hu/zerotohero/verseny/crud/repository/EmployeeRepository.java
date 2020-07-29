package hu.zerotohero.verseny.crud.repository;

import hu.zerotohero.verseny.crud.util.EmployeeJob;
import hu.zerotohero.verseny.crud.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Query("select emp from Employee emp join emp.worksAt loc where emp.job = :job and loc.id = :locationId")
    Iterable<Employee> findAllByLocationAndJob(@Param("locationId") Long locationId, @Param("job")EmployeeJob job);

    @Query("select emp from Employee emp join emp.operates equipment where equipment.id = :equipmentId")
    Optional<Employee> findByOperatedEquipment(@Param("equipmentId") Long equipmentId);

    @Query(value = "select max(emp.salary) from Employee emp join emp.worksAt loc where loc.id = :locationId and emp.job <> 'MANAGER'")
    Optional<Integer> findMaxSalaryOfNonManagersByLocation(@Param("locationId") Long locationId);

    @Query("select avg(emp.salary) from Employee emp join emp.worksAt loc where loc.id = :locationId and emp.job = :job")
    Optional<Double> findAvgSalaryByLocationAndJob(@Param("locationId") Long locationId, @Param("job")EmployeeJob job);
}
