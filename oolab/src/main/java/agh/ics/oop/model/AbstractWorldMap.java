package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap {
    protected final Map <Vector2d,Animal> animalMap =  new HashMap<Vector2d,Animal>();
    protected final MapVisualizer mapVisualizer = new MapVisualizer(this);
    protected Vector2d upperRight;
    protected Vector2d lowerLeft;


    public abstract boolean canMoveTo(Vector2d position);

    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getPosition())){
            animalMap.put(animal.getPosition(), animal);
            return true;
        }
        else{return false;}
    }

    public boolean isOccupied(Vector2d position){
        return animalMap.containsKey(position);
    }

    public void move(Animal animal, MoveDirection direction) {
        if (this.isOccupied(animal.getPosition())){
            animalMap.remove(animal.getPosition());
            animal.move(direction,this);
            animalMap.put(animal.getPosition(), animal);
        }
    }

    public abstract WorldElement objectAt(Vector2d position);

    public String toString() {
        return mapVisualizer.draw(lowerLeft,upperRight);
    }

    @Override
    public List<WorldElement> getElements() {
        return new ArrayList<WorldElement>(animalMap.values());
    }
}
