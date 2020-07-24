package hu.zerotohero.verseny.crud.repository;

import hu.zerotohero.verseny.crud.entity.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

}
