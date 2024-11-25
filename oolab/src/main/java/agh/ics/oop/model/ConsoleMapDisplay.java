package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener{
    private int updatesCounter = 0;

    @Override
    public void mapChanged(WorldMap map, String message) {
        updatesCounter++;
        System.out.printf("updates so far: %d %n", updatesCounter);
        System.out.println(message);
        System.out.println(map.toString());
    }
}
