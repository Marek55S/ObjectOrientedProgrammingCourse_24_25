package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractWorldMap implements WorldMap {
    protected final Map <Vector2d,Animal> animals =  new HashMap<Vector2d,Animal>();
    protected final MapVisualizer mapVisualizer = new MapVisualizer(this);


    public boolean canMoveTo(Vector2d position){
        return !animals.containsKey(position);
    }

    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(), animal);
            return true;
        }
        else{return false;}
    }

    public boolean isOccupied(Vector2d position){
        return animals.containsKey(position);
    }

    public void move(Animal animal, MoveDirection direction) {
        if (this.isOccupied(animal.getPosition())){
            animals.remove(animal.getPosition());
            animal.move(direction,this);
            animals.put(animal.getPosition(), animal);
        }
    }

    public WorldElement objectAt(Vector2d position){
        return animals.get(position);
    }

    public abstract String toString();

    @Override
    public Collection<WorldElement> getElements() {
        return new ArrayList<>(animals.values());
    }
}
