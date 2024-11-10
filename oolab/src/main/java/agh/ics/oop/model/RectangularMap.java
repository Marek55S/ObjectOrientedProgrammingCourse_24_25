package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap{
    private final Map<Vector2d,Animal> animals = new HashMap<>();
    private final int mapWidth;
    private final int mapHeight;
    private static final Vector2d LOWER_LEFT = new Vector2d(0, 0);
    private final Vector2d upperRight;
    private final MapVisualizer mapVisualizer = new MapVisualizer(this);

    public RectangularMap(int width, int height) {
        mapWidth = width;
        mapHeight = height;
        upperRight = new Vector2d(mapWidth-1, mapHeight-1);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return isInMapBounds(position) && !this.isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(), animal);
            return true;
        }
        else{return false;}
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (animals.containsKey(animal.getPosition())){
            animals.remove(animal.getPosition());
            animal.move(direction,this);
            animals.put(animal.getPosition(), animal);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(LOWER_LEFT,upperRight);
    }

    protected boolean isInMapBounds(Vector2d position) {
        return position.follows(LOWER_LEFT) && position.precedes(upperRight);
    }
}
