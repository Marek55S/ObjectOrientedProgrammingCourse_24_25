package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");
        MoveDirection[] directions = OptionParser.parseDirection(args);
        run(directions);
        System.out.println("Stop");
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
        MapDirection direction1 = MapDirection.EAST;
        System.out.println(direction1.toString());
        System.out.println(direction1.next());
        MapDirection direction2 = MapDirection.NORTH;
        System.out.println(direction2.toString());
        System.out.println(direction2.previous());
        System.out.println(direction1.toUnitVector());
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
