package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");
        MoveDirection[] directions = OptionParser.directionparser(args);
        run(directions);
        System.out.println("Stop");
    }

    public static void run(MoveDirection[] direction) {
        for(MoveDirection d : direction) {
            switch (d){
                case f -> System.out.println("Zwierzak idzie do przodu");
                case b -> System.out.println("Zwierzak idzie do tyłu");
                case r -> System.out.println("Zwierzak skręca w prawo");
                case l -> System.out.println("Zwierzak skręca w lewo");
            }
        }
    }
}
