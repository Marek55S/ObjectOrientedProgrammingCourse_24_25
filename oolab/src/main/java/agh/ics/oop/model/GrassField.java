package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;

public class GrassField implements WorldMap{
    private static final Vector2d LOWER_LEFT = new Vector2d(0,0);
    private final int grassNumber;
    private final Map<Vector2d,Animal> animalMap = new HashMap<Vector2d,Animal>();
    private final Map<Vector2d,Grass> grassMap = new HashMap<Vector2d,Grass>();
    private Vector2d grassBound = new Vector2d(0,0);;
    private Vector2d animalBound = new Vector2d(0,0);;
    // moze lower left nie bedzie final, zalezy co bedzie dalej

    public GrassField(int grassNumber) {
        this.grassNumber = grassNumber;
    }

    //implementacja losowego rozmieszczania Grass na mapie
    private void placeGrass(){
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animalMap.containsKey(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getPosition())){
            animalMap.put(animal.getPosition(), animal);
            return true;
        }
        else{return false;}
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (animalMap.containsKey(animal.getPosition())){
            animalMap.remove(animal.getPosition());
            animal.move(direction,this);
            animalMap.put(animal.getPosition(), animal);
        }
    }

    // do sprawdzenia czy ma dzialac takze na trawe
    @Override
    public boolean isOccupied(Vector2d position) {
        return this.animalMap.containsKey(position) || this.grassMap.containsKey(position);
    }

    // zastanow sie co zrobic z trawa i co sie dzieje
    // jak jednoczesnie jest trawa i zwierze na tej samej pozycji
    @Override
    public WorldElement objectAt(Vector2d position) {
        return animalMap.get(position);
    }

    public String toString(){
        return (" ");
    }
}
