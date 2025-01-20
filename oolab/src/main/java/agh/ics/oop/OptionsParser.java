package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionsParser {
    public static List<MoveDirection> parseDirection(String[] stringDirections) {
        return Stream.of(stringDirections)
                .map(OptionsParser::makeMoveDirection)
                .collect(Collectors.toList());
    }


    static private MoveDirection makeMoveDirection(String stringDirection) {
        return switch (stringDirection) {
            case "f" -> MoveDirection.FORWARD;
            case "b" -> MoveDirection.BACKWARD;
            case "l" -> MoveDirection.LEFT;
            case "r" -> MoveDirection.RIGHT;
            default -> throw new IllegalArgumentException("Illegal move specification: " + stringDirection);
        };
    }
}
