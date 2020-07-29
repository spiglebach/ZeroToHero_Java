package hu.zerotohero.verseny.crud.dto;

import hu.zerotohero.verseny.crud.exception.EmptyAttributeException;
import hu.zerotohero.verseny.crud.exception.LocationAddressNotValidException;
import hu.zerotohero.verseny.crud.exception.LocationNameNotValidException;

public class LocationDTO {
    private String name;
    private String address;

    public LocationDTO(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void validate() throws EmptyAttributeException,
                    LocationNameNotValidException,
                    LocationAddressNotValidException {
        if (name == null || address == null || name.trim().equals("") || address.trim().equals("")) {
            throw new EmptyAttributeException();
        }
        if (name.split(" ").length > 2) {
            throw new LocationNameNotValidException();
        }
        if (!address.split(" ")[0].matches("^[0-9]{4}.*")) {
            throw new LocationAddressNotValidException();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
