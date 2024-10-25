package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionParser {
    public static MoveDirection[] parsedirection(String[] stringdirections) {
        List<MoveDirection> directions = new ArrayList<>();
        for (String direction : stringdirections) {
            switch (direction) {
                case "f" -> directions.add(MoveDirection.forward);
                case "b" -> directions.add(MoveDirection.backward);
                case "l" -> directions.add(MoveDirection.left);
                case "r" -> directions.add(MoveDirection.right);
                default -> {}
            }
            }
        return directions.toArray(new MoveDirection[0]);
    }
}
