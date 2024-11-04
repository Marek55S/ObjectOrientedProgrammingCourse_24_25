package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<Animal> animalsList = new ArrayList<>();
    private List<MoveDirection> movesList;

    public Simulation(List<Vector2d> startingPositions, List<MoveDirection>moves) {
        for (Vector2d startingPosition : startingPositions) {
            animalsList.add(new Animal(startingPosition));
        }
        movesList = moves;
    }

    public List<Animal> getAnimalsList() {
        return animalsList;
    }

    public void run(){
        int numberOfAnimals = animalsList.size();
        for(int i = 0;i<movesList.size();i++){
            int animalNumber = i%numberOfAnimals;
            animalsList.get(animalNumber).move(movesList.get(i));
            System.out.printf("Zwierzę %d : %s%n", animalNumber, animalsList.get(animalNumber).getPosition().toString());
        }
    }

}
