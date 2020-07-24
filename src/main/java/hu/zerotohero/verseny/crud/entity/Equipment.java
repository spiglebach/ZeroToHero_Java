package hu.zerotohero.verseny.crud.entity;

import hu.zerotohero.verseny.crud.util.EquipmentType;

import javax.persistence.*;

@Entity
public class Equipment {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private EquipmentType type;

    @ManyToOne
    private Location locatedAt;

    public Equipment() {

    }

    public Equipment(String name, EquipmentType type, Location locatedAt) {
        this.name = name;
        this.type = type;
        this.locatedAt = locatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Location getLocatedAt() {
        return locatedAt;
    }

    public void setLocatedAt(Location locatedAt) {
        this.locatedAt = locatedAt;
    }
}
