package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public class GrassField implements WorldMap{
    private final Map<Vector2d,Animal> animalMap = new HashMap<Vector2d,Animal>();
    private final Map<Vector2d,Grass> grassMap = new HashMap<Vector2d,Grass>();
    private Vector2d grassBound = new Vector2d(0,0);
    private Vector2d upperRight = new Vector2d(0,0);
    private Vector2d lowerLeft = new Vector2d(0,0);
    private final Random generator = new Random();
    private final MapVisualizer mapVisualizer = new MapVisualizer(this);

    // moze lower left nie bedzie static final, zalezy co bedzie dalej

    public GrassField(int grassNumber) {
        placeGrass(grassNumber);
    }


    //implementacja losowego rozmieszczania Grass na mapie
    private void placeGrass(int grassNumber){
        while (grassMap.size()<grassNumber){
            int x =generator.nextInt(1+(int) Math.sqrt(grassNumber*10));
            int y =generator.nextInt(1+(int) Math.sqrt(grassNumber*10));
            Vector2d newGrassPosition = new Vector2d(x,y);
            if (!grassMap.containsKey(newGrassPosition)){
                grassMap.put(newGrassPosition,new Grass(newGrassPosition));
                grassBound = grassBound.upperRight(newGrassPosition);
            }
        }
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

    // mozeby tu jakos liczyc na bierzaco animalBound
    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (this.isOccupied(animal.getPosition())){
            animalMap.remove(animal.getPosition());
            animal.move(direction,this);
            animalMap.put(animal.getPosition(), animal);
        }
    }

    // do sprawdzenia czy ma dzialac takze na trawe
    @Override
    public boolean isOccupied(Vector2d position) {
        return this.animalMap.containsKey(position);
    }

    // zastanow sie co zrobic z trawa i co sie dzieje
    // jak jednoczesnie jest trawa i zwierze na tej samej pozycji
    @Override
    public WorldElement objectAt(Vector2d position) {
        return (!animalMap.containsKey(position)) ? animalMap.get(position) : grassMap.get(position);
    }

    //sprawdz czy dobrze mapa sie generuje
    // cos chyba map visualizer nie lubi sie z mapa
    @Override
    public String toString(){
        this.findMapBound();
        return mapVisualizer.draw(lowerLeft,upperRight);
    }

    // mozna sprobowac liczyc to w move, zeby nie wykonywac tyle operacji za kazdym razem
    private void findMapBound(){
        upperRight = grassMap.keySet().iterator().next();
        lowerLeft = grassMap.keySet().iterator().next();
        for (Vector2d position : animalMap.keySet()){
            upperRight = upperRight.upperRight(position);
            lowerLeft = lowerLeft.lowerLeft(position);
        }
        upperRight.upperRight(grassBound);
        lowerLeft.lowerLeft(grassBound);
    }

    protected int grassCount(){
        return grassMap.size();
    }

    protected Vector2d grassPosition(){
        return grassMap.keySet().iterator().next();
    }

}
