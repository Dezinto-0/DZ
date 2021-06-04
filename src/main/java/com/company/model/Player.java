package com.company.model;

import java.util.List;

public class Player {
    private Location location;
    private Inventory inventory;
    private List<Combo> combos;

    //Осмотреться
    public void lookAround() {
        location.lookAround();
    }

    //Идти в направлении
    public void go(Direction direction) {
        Location newLocation = location.getDirection().get(direction);
        if(newLocation != null) {
            location = newLocation;
            lookAround();
        }
        else {
            System.out.println("Вы не можете туда пойти.");
        }
    }

    //Взять предмет
    public void take(String itemName) {
        Item item = location.getInventory().find(itemName);
        if (item == null) {
            System.out.println("Предмет не найден");
        }
        else if (!item.isMoveable()) {
            System.out.println("Невозможно взять предмет");
        }
        else  if(item != null && item.isMoveable()) {
            System.out.print("У вас есть ");
            item.showName();
            location.getInventory().remove(item);
            inventory.add(item);
        }
    }

    //Использовать
    public void use(Combo combo){
        Item object = inventory.find(combo.getObject().getName());
        Item subject = location.getInventory().find(combo.getSubject().getName());
        if (object != null && subject != null) {
            System.out.println(combo.getMessage());
        }
        else {
            System.out.println("Комбо невозможно.");
        }
    }

    //Инвентарь
    public void inventory(){
        if (inventory != null){
            inventory.showName();
        }
        else {
            System.out.println("Пусто");
        }
    }

    private Item findItem(String itemName) {
        Item object = null;
        object = inventory.find(itemName);
        if(object == null) {
            object = location.getInventory().find(itemName);
        }
        return object;
    }

    public Location getLocation() {
        return location;
    }

    public Player(Location location, Inventory inventory, List<Combo> combos) {
        this.location = location;
        this.inventory = inventory;
        this.combos = combos;
    }
}
