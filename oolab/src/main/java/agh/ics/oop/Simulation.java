package agh.ics.oop;

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
            Animal newAnimal = new Animal(startingPosition);
            if (map.place(newAnimal)){animalsList.add(newAnimal);}
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
