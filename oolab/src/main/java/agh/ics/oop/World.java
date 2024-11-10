package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

public class World {
    public static void main(String[] args) {
        /*List<MoveDirection> directions = OptionsParser.parseDirection(args);
        List<Animal> animals = List.of(new Animal(),new Animal(new Vector2d(3,4)));
        WorldMap<Animal, Vector2d> map = new RectangularMap(5,5);
        Simulation<Animal, Vector2d>  simulation = new Simulation<>(animals, directions,map);
        simulation.run();*/
        List<String> texts = List.of("Ala", "ma", "sowoniedźwiedzia");
        WorldMap<String,Integer> map = new TextMap<>();
        for (String text : texts) {
            map.place(text);
        }
        System.out.println(map);
        map.move("ma",MoveDirection.LEFT);
        System.out.println(map);
        map.move("ma",MoveDirection.LEFT);
        System.out.println(map);

    }

    public static void run(MoveDirection[] directions) {
        for(MoveDirection direction: directions) {
            switch (direction){
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case LEFT -> System.out.println("Zwierzak skręca w lewo");
                case RIGHT -> System.out.println("Zwierzak skręca w prawo");
            }
        }
    }
}
