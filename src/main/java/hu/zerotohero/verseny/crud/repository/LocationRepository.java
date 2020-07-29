package hu.zerotohero.verseny.crud.repository;

import hu.zerotohero.verseny.crud.entity.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
    @Query("select count(emp) from Location loc join Employee emp on loc = emp.worksAt where loc.id = :locationId")
    Optional<Integer> getEmployeeCountAtLocation(@Param(value = "locationId") Long locationId);

    @Query("select count(eq) from Location loc join Equipment eq on loc = eq.locatedAt where loc.id = :locationId")
    Optional<Integer> getEquipmentCountAtLocation(@Param(value = "locationId") Long locationId);
}
