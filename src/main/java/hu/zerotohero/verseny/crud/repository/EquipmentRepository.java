package hu.zerotohero.verseny.crud.repository;

import hu.zerotohero.verseny.crud.util.EquipmentType;
import hu.zerotohero.verseny.crud.entity.Equipment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends CrudRepository<Equipment, Long> {
    @Query("from Equipment eq join eq.locatedAt loc where loc.id = :locationId")
    Iterable<Equipment> findAllByLocation(@Param("locationId") Long locationId);

    @Query("from Equipment eq join eq.locatedAt loc where loc.id = :locationId and type = :type")
    Iterable<Equipment> findAllByLocationAndType(@Param("locationId") Long locationId, @Param("type") EquipmentType type);
}
