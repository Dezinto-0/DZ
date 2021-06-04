package com.company.model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items = new ArrayList<>();

    public void add(Item item) {
        this.items.add(item);
    }

    public void remove(Item item) {
        this.items.remove(item);
    }

    public Item find(String itemName) {
        for (Item item: items) {
            if(item.getName().endsWith(itemName)){
                return item;
            }
        }
        return null;
    }

    public void showDescription() {
        for(Item item: items) {
            item.showDescription();
        }

    }
    public void showName() {
        for (Item item : items) {
            item.showName();
        }
    }

    public Inventory(List<Item> items) {
        this.items = items;
    }
}
