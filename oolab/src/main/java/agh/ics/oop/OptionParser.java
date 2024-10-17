package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionParser {
    public static MoveDirection[] directionparser(String[] args) {
        List<MoveDirection> directions = new ArrayList<>();
        for (String arg : args) {
            try{
            MoveDirection direction = MoveDirection.valueOf(arg);
            directions.add(direction);
            } catch (IllegalArgumentException e) {
                System.out.println(arg + " is not a valid move direction");
                throw new RuntimeException(e);
            }
        }
        return directions.toArray(new MoveDirection[0]);
    }
}
