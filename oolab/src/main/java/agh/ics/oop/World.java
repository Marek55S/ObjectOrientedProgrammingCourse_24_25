package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.sql.SQLOutput;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");
        MoveDirection[] directions = OptionParser.parsedirection(args);
        run(directions);
        System.out.println("Stop");
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
    }

    public static void run(MoveDirection[] directions) {
        for(MoveDirection direction: directions) {
            switch (direction){
                case forward -> System.out.println("Zwierzak idzie do przodu");
                case backward -> System.out.println("Zwierzak idzie do tyłu");
                case right -> System.out.println("Zwierzak skręca w prawo");
                case left -> System.out.println("Zwierzak skręca w lewo");
            }
        }
    }
}
