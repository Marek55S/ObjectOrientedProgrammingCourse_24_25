package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public class GrassField extends AbstractWorldMap implements WorldMap{
    private final Map<Vector2d,Grass> grassMap = new HashMap<Vector2d,Grass>();
    private Vector2d grassBoundUR;
    private Vector2d grassBoundLL;
    private final Random generator = new Random();

    public GrassField(int grassNumber) {
        grassBoundUR = new Vector2d(0,0);
        grassBoundLL = new Vector2d((int) Math.sqrt(grassNumber*10),(int) Math.sqrt(grassNumber*10));
        placeGrass(grassNumber);
    }

    private void placeGrass(int grassNumber){
        while (grassMap.size()<grassNumber){
            int x =generator.nextInt((int) Math.sqrt(grassNumber*10));
            int y =generator.nextInt((int) Math.sqrt(grassNumber*10));
            Vector2d newGrassPosition = new Vector2d(x,y);
            if (!grassMap.containsKey(newGrassPosition)){
                grassMap.put(newGrassPosition,new Grass(newGrassPosition));
                grassBoundUR = grassBoundUR.upperRight(newGrassPosition);
                grassBoundLL = grassBoundLL.lowerLeft(newGrassPosition);
            }
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animals.containsKey(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.animals.containsKey(position) || this.grassMap.containsKey(position);
    }


    @Override
    public WorldElement objectAt(Vector2d position) {
        return (animals.containsKey(position)) ? animals.get(position) : grassMap.get(position);
    }

    @Override
    public String toString(){
        this.findMapBound();
        return super.toString();
    }

    private void findMapBound(){
        upperRight = grassBoundUR;
        lowerLeft = grassBoundLL;
        for (Vector2d position : animals.keySet()){
            upperRight = upperRight.upperRight(position);
            lowerLeft = lowerLeft.lowerLeft(position);
        }
    }

    protected Vector2d getGrassPosition(){
        return grassMap.keySet().iterator().next();
    }

    @Override
    public List<WorldElement> getElements(){
        List<WorldElement> elements = super.getElements();
        elements.addAll(grassMap.values());
        return elements;
    }

}
