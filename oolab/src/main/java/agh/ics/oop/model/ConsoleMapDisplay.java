package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener{
    private int updatesCounter = 0;

    // cos tu trzeba pozmieniac bo wyswietlanie zle dziala
    // moze nie tak wykorzystac synchronized albo cos jeszcze dodac
    @Override
    public synchronized void mapChanged(WorldMap map, String message) {
        updatesCounter++;
        System.out.printf("updates so far: %d %n", updatesCounter);
        System.out.println(message);
        System.out.println("map ID: "+ map.getID().toString());
        System.out.println(map.toString());
    }
}
