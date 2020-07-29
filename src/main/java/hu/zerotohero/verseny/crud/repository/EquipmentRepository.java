package hu.zerotohero.verseny.crud.repository;

import hu.zerotohero.verseny.crud.entity.Employee;
import hu.zerotohero.verseny.crud.entity.Equipment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentRepository extends CrudRepository<Equipment, Long> {
    @Query("select emp from Equipment eq join Employee emp on eq = emp.operates and eq.id = :equipmentId")
    Optional<Employee> findEmployeeByOperatedEquipment(@Param("equipmentId") Long equipmentId);
}
