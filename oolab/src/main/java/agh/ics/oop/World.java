package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");
        MoveDirection[] directions = OptionParser.parsedirection(args);
        run(directions);
        System.out.println("Stop");
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
