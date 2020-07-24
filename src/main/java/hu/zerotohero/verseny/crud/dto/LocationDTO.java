package hu.zerotohero.verseny.crud.dto;

import hu.zerotohero.verseny.crud.exception.EmptyAttributeException;

public class LocationDTO {
    private String name;
    private String address;

    public LocationDTO(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void validate() throws EmptyAttributeException {
        if (name == null || address == null || name.trim().equals("") || address.trim().equals("")) {
            throw new EmptyAttributeException();
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
