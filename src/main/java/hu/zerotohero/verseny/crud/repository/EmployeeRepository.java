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
    @Query("select emp from Employee emp join emp.worksAt loc where loc.id = :locationId")
    Iterable<Employee> findAllByLocation(@Param("locationId") Long locationId);

    @Query("select emp from Employee emp join emp.worksAt loc where emp.job = :job and loc.id = :locationId")
    Iterable<Employee> findAllByLocationAndJob(@Param("locationId") Long locationId, @Param("job")EmployeeJob job);

    @Query("select emp from Employee emp join emp.operates equipment where equipment.id = :equipmentId")
    Optional<Employee> findByOperatedEquipment(@Param("equipmentId") Long equipmentId);
}
