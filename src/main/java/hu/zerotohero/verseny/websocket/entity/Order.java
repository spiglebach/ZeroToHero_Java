package hu.zerotohero.verseny.websocket.entity;

public class Order {
    private String name;

    public Order() {

    }

    public Order(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
