package com.company.model;

import java.util.Map;

public class Location {
    private String name;
    private String description;
    private Inventory inventory;
    private Map<Direction, Location> direction;

    public void lookAround() {
        System.out.print(description);
        inventory.showDescription();
        System.out.println();
    }

    public Map<Direction, Location> getDirection() {
        return direction;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Location(String name, String description, Inventory inventory, Map<Direction, Location> direction) {
        this.name = name;
        this.description = description;
        this.inventory = inventory;
        this.direction = direction;
    }

    public void setDirection(Map<Direction, Location> direction) {
        this.direction = direction;
    }
}
