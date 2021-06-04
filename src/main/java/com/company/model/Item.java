package com.company.model;

public class Item {
    private String name;
    private String description;
    private Moveable moveable;

    public Item(String name, String description, Moveable moveable) {
        this.name = name;
        this.description = description;
        this.moveable = moveable;
    }

    public String getName() {
        return name;
    }

    public void showDescription() {
        System.out.print(description);
    }

    public void showName() {
        System.out.println(name);
        }

    public boolean isMoveable() {
        return moveable.equals(Moveable.MOBILE);
    }
}
