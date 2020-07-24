package hu.zerotohero.verseny.crud.dto;

import hu.zerotohero.verseny.crud.util.EquipmentType;
import hu.zerotohero.verseny.crud.exception.EmptyAttributeException;

public class EquipmentDTO {
    String name;
    EquipmentType type;
    Long locatedat;

    public EquipmentDTO(String name, EquipmentType type, Long locatedat) {
        this.name = name;
        this.type = type;
        this.locatedat = locatedat;
    }

    public void validate() throws EmptyAttributeException {
        if (name == null || type == null || locatedat == null) {
            throw new EmptyAttributeException();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EquipmentType getType() {
        return type;
    }

    public void setType(EquipmentType type) {
        this.type = type;
    }

    public Long getLocatedat() {
        return locatedat;
    }

    public void setLocatedat(Long locatedat) {
        this.locatedat = locatedat;
    }
}
