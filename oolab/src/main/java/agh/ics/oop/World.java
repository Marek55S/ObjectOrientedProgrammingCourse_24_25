package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

public class World {
    public static void main(String[] args) {
        try {
            List<MoveDirection> directions = OptionsParser.parseDirection(args);
            List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4), new Vector2d(2,2));
            WorldMap map = new GrassField(10);
            Simulation simulation = new Simulation(positions, directions,map);
            simulation.run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
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
