package agh.ics.oop;

import agh.ics.oop.exceptions.IncorrectPositionException;
import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Animal> animalsList = new ArrayList<>();
    private final List<MoveDirection> movesList;
    private final WorldMap map;

    public Simulation(List<Vector2d> startingPositions, List<MoveDirection>moves, WorldMap newMap) {
        map = newMap;
        movesList = moves;
        for (Vector2d startingPosition : startingPositions) {
            try{
            Animal newAnimal = new Animal(startingPosition);
            map.place(newAnimal);
            animalsList.add(newAnimal);
        }catch(IncorrectPositionException e){
                System.out.println(e.getMessage());
            }
        }
    }

    protected List<Animal> getAnimalsList() {
        return animalsList;
    }


    public void run(){
        int numberOfAnimals = animalsList.size();
        for(int i = 0;i<movesList.size();i++){
            int animalNumber = i%numberOfAnimals;
            map.move(animalsList.get(animalNumber), movesList.get(i));
            System.out.println(map);
        }
    }

}
