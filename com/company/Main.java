package com.company;

import com.company.model.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Item bucket = new Item("ведро","На полу валяется ведро. ", Moveable.MOBILE);
        Item chain = new Item("цепь", "Рядом лежит ржавая цепь. ", Moveable.MOBILE);
        Item torch = new Item("горелка", "В углу гигантская горелка. ", Moveable.STATIONARY);
        Item well = new Item("колодец", "Прямо по курсу находится колодец. ", Moveable.STATIONARY);
        Item wizard = new Item("волшебник", "А вот и сам волшебник - громко храпит на кровати. ", Moveable.STATIONARY);
        Item bucketWithChain = new Item("ведро с цепью", "цепь припаяна к ведру", Moveable.MOBILE);
        Item bucketWithWater = new Item("ведро с водой", "ведро с водой", Moveable.MOBILE);

        Scanner scanner = new Scanner(System.in);
        String comand = "осмотреться";

        List<Item> livingRoomItems = new ArrayList<>();
        Inventory livingRoomInventory = new Inventory(livingRoomItems);
        livingRoomInventory.add(bucket);
        livingRoomInventory.add(wizard);

        List<Item> atticItems = new ArrayList<>();
        Inventory atticInventory = new Inventory(atticItems);
        atticInventory.add(torch);

        List<Item> gardenItems = new ArrayList<>();
        Inventory gardenInventory = new Inventory(gardenItems);
        gardenInventory.add(chain);
        gardenInventory.add(well);

        List<Item> playerItems = new ArrayList<>();
        Inventory playerInventory = new Inventory(playerItems);

        Location livingRoom = new Location("гостиная", "Вы находитесь в гостиной в доме волшебника. " +
                "На западе от вас есть дверь, рядом - лестница наверх. ",
                livingRoomInventory, null);

        Location attic = new Location("чердак", "Вы на чердаке старого дома. Вниз ведет лестница. ",
                atticInventory, null);

        Location garden = new Location("сад", "Вы в прекрасном саду. На востоке - дверь в дом. ",
                gardenInventory, null);

        Map<Direction, Location> livingRoomDirections = new HashMap<>();
        livingRoomDirections.put(Direction.UP, attic);
        livingRoomDirections.put(Direction.WEST, garden);
        livingRoom.setDirection(livingRoomDirections);

        Map<Direction, Location> atticDirections = new HashMap<>();
        atticDirections.put(Direction.DOWN, livingRoom);
        attic.setDirection(atticDirections);

        Map<Direction, Location> gardenDirections = new HashMap<>();
        gardenDirections.put(Direction.EAST, livingRoom);
        garden.setDirection(gardenDirections);

        Player player = new Player(livingRoom,playerInventory, null);

        Combo bucketToWizard = new Combo(bucket, wizard, bucket, "В ведре пусто.");
        Combo bucketToWell = new Combo(bucket, well, bucket, "До воды слишком далеко.");
        Combo bucketToChain = new Combo(bucket, chain, bucket, "Не получается соединить.");
        Combo bucketToTorch = new Combo(bucket, torch, bucket, "Ведро нагрелось и обжигает пальцы.");

        Combo chainToWizard = new Combo(chain, wizard, chain, "Удары сыпятся на волшебника, но он не просыпается.");
        Combo chainToWell = new Combo(chain, well, chain, "Цепь достает до воды.");
        Combo chainToTorch = new Combo(chain, torch, chain, "Цепь нагрелась и обжигает пальцы.");

        Combo chainBucketTorch = new Combo(chain, torch, bucketWithChain, "Цепь надежно приварена к ведру.");
        Combo bucketWithChainToWell = new Combo(bucketWithChain, well, bucketWithWater, "Держа ведро за цепь, " +
                "вы опускаете его вколодец и поднимаете полным до краев.");
        Combo bucketWithWaterToWizard = new Combo(bucketWithWater, wizard, bucket, "Волшебник вскакивает и " +
                "начинает отряхиваться. Приведя себя в порядок, он благодарит вас за помощь и протягивает вам " +
                "магический кристалл. Вы выиграли!");
        while (!(comand.equals("выход"))) {
            if (comand.equals("осмотреться") || comand.equals("1")) {
                player.lookAround();
            }
            else if (comand.equals("идти вверх") || comand.equals("2")){
                player.go(Direction.UP);
            }
            else if (comand.equals("идти вниз") || comand.equals("3")){
                player.go(Direction.DOWN);
            }
            else if (comand.equals("идти запад") || comand.equals("4")){
                player.go(Direction.WEST);
            }
            else if (comand.equals("идти восток") || comand.equals("5")){
                player.go(Direction.EAST);
            }
            else if (comand.equals("идти север") || comand.equals("6")){
                player.go(Direction.NORTH);
            }
            else if (comand.equals("идти юг") || comand.equals("7")){
                player.go(Direction.UP);
            }
            else if (comand.equals("инвентарь") || comand.equals("8")){
                player.inventory();
            }
            else if (comand.equals("взять ведро") || comand.equals("9")){
                player.take("ведро");
            }
            else if (comand.equals("взять горелка")){
                player.take("горелка");
            }
            else if (comand.equals("взять цепь")){
                player.take("цепь");
            }
            else if (comand.equals("взять колодец")){
                player.take("колодец");
            }
            else if (comand.equals("использовать ведро на волшебник")) {
                if (playerItems.contains(bucketWithWater)) {
                    player.use(bucketWithWaterToWizard);
                    return;
                }
                else {
                    player.use(bucketToWizard);
                }
            }
            else if (comand.equals("использовать ведро на колодец")) {
                if (playerItems.contains(bucketWithChain)) {
                    player.use(bucketWithChainToWell);
                    playerInventory.remove(bucketWithChain);
                    playerInventory.add(bucketWithWater);
                }
                else {
                    player.use(bucketToWell);
                }
            }
            else if (comand.equals("использовать цепь на волшебник")) {
                if (playerItems.contains(bucket)) {
                    player.use(chainToWizard);
                }
                else if (playerItems.contains(bucketWithChain)) {
                    player.use(bucketWithChainToWell);
                }
            }
            else if (comand.equals("использовать цепь на колодец")) {
                player.use(chainToWell);
            }
            else if (comand.equals("использовать цепь на ведро") || comand.equals("использовать цепь на горелка") ||
                    comand.equals("использовать ведро на цепь") || comand.equals("использовать ведро на горелка")) {
                if (player.getLocation().equals(attic) && playerItems.contains(bucket) && playerItems.contains(chain)) {
                    player.use(chainBucketTorch);
                    playerInventory.remove(bucket);
                    playerInventory.remove(chain);
                    playerInventory.add(bucketWithChain);
                }
                else if (player.getLocation().equals(attic) && playerItems.contains(bucket)) {
                    player.use(bucketToTorch);
                }
                else if (player.getLocation().equals(attic) && playerItems.contains(chain)) {
                    player.use(chainToTorch);
                }
                else if (playerItems.contains(bucket) && playerItems.contains(chain)) {
                    player.use(bucketToChain);
                }
                else {
                    System.out.println("Комбо невозможно.");
                }
            }
            else if (comand.equals("использовать ")) {
                player.use(chainToWell);
            }
            else {
                System.out.println("Команда не найдена");
            }
            comand = scanner.nextLine();
        }
    }
}
