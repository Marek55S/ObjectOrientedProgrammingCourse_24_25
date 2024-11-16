package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap implements WorldMap{

    public RectangularMap(int width, int height) {
        upperRight = new Vector2d(width-1, height-1);
        lowerLeft = new Vector2d(0, 0);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return this.isInMapBounds(position) && !this.isOccupied(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animalMap.get(position);
    }

    protected boolean isInMapBounds(Vector2d position) {
        return position.follows(lowerLeft) && position.precedes(upperRight);
    }


}
