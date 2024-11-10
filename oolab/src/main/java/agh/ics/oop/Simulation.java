package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation<T,P> {
    private final List<T> animalsList = new ArrayList<>();
    private final List<MoveDirection> movesList;
    private final WorldMap<T,P> map;

    public Simulation(List<T> animalsList, List<MoveDirection>moves, WorldMap<T,P> newMap) {
        map = newMap;
        movesList = moves;
        this.animalsList.addAll(animalsList);
        for (T animal : animalsList) {
            map.place(animal);
        }
    }

    protected List<T> getAnimalsList() {
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
