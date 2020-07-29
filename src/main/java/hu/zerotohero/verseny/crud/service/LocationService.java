package hu.zerotohero.verseny.crud.service;

import hu.zerotohero.verseny.crud.dto.LocationDTO;
import hu.zerotohero.verseny.crud.entity.Location;
import hu.zerotohero.verseny.crud.exception.*;
import hu.zerotohero.verseny.crud.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getLocations() {
        ArrayList<Location> locations = new ArrayList<>();
        for (Location location : locationRepository.findAll()) {
            locations.add(location);
        }
        return locations;
    }

    public Location newLocation(LocationDTO locationDTO) throws EmptyAttributeException,
                                                                LocationNameNotValidException,
                                                                LocationAddressNotValidException {
        locationDTO.validate();
        Location location = new Location(locationDTO.getName(), locationDTO.getAddress());
        return locationRepository.save(location);
    }

    public Location updateLocation(Long id, LocationDTO locationDTO)
                throws EmptyAttributeException,
                        LocationNameNotValidException,
                        LocationAddressNotValidException,
                        NoSuchEntityException {
        locationDTO.validate();
        Location location = findById(id);
        if (location == null) {
            throw new NoSuchEntityException(Location.class);
        }
        location.setName(locationDTO.getName());
        location.setAddress(locationDTO.getAddress());
        return locationRepository.save(location);
    }

    public Long deleteLocation(Long id) throws NoSuchEntityException, LocationNotEmptyException {
        Location location = findById(id);
        if (location == null) {
            throw new NoSuchEntityException(Location.class);
        }
        if (getEquipmentCountByLocation(id) != 0
            || getEmployeeCountByLocation(id) != 0) {
            throw new LocationNotEmptyException();
        }
        locationRepository.deleteById(id);
        return id;
    }

    public Location findById(Long id) {
        Optional<Location> location = locationRepository.findById(id);
        return location.orElse(null);
    }

    public int getEmployeeCountByLocation(Long locationId) {
        return locationRepository.getEmployeeCountAtLocation(locationId).orElse(0);
    }

    public int getEquipmentCountByLocation(Long locationId) {
        return locationRepository.getEquipmentCountAtLocation(locationId).orElse(0);
    }

}
