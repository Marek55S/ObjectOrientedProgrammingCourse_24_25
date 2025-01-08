package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class OptionsParser {
    public static List<MoveDirection> parseDirection(String[] stringDirections) {
//        List<MoveDirection> directions = new ArrayList<>();
//        for (String direction : stringDirections) {
//            switch (direction) {
//                case "f" -> directions.add(MoveDirection.FORWARD);
//                case "b" -> directions.add(MoveDirection.BACKWARD);
//                case "l" -> directions.add(MoveDirection.LEFT);
//                case "r" -> directions.add(MoveDirection.RIGHT);
//                default -> {throw new IllegalArgumentException("Illegal move specification: " + direction);}
//            }
//            }
//        return directions;
        List<String> directions = List.of(stringDirections);
     return directions.stream()
    }

    private MoveDirection makeMoveDirection(String stringDirection) {
        MoveDirection resutDirection = null;
        switch (stringDirection) {
            case "f" -> resutDirection = MoveDirection.FORWARD;
            case "b" -> resutDirection = MoveDirection.BACKWARD;
            case "l" -> resutDirection = MoveDirection.LEFT;
            case "r" -> resutDirection = MoveDirection.RIGHT;
            default -> resutDirection = null;
        }
        return resutDirection;
    }
}
