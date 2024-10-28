package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionParserTest {

    @Test
    void parseDirectionAllValidDirections() {
        //given
        String[] validDirections = {"f","b","l","r"};
        MoveDirection[] expectedArray = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT};

        // when
        MoveDirection[] parsedArray = OptionParser.parseDirection(validDirections);

        //then
        assertArrayEquals(expectedArray, parsedArray);
    }

    @Test
    void parseDirectionEmptyDirections() {
        //given
        String[] emptyArray = {};
        MoveDirection[] expectedArray = {};

        //when
        MoveDirection[] parsedArray = OptionParser.parseDirection(emptyArray);

        //then
        assertArrayEquals(expectedArray, parsedArray);
    }

    @Test
    void parseDirectionALLInvalidDirections(){
        //given
        String[] invalidDirections = {"x","y","z"};
        MoveDirection[] expectedArray = {};

        //when
        MoveDirection[] parsedArray = OptionParser.parseDirection(invalidDirections);

        //then
        assertArrayEquals(expectedArray, parsedArray);
    }

    @Test
    void parseDirectionMixedDirections() {
        //given
        String[] mixedDirections = {"x","b","y","z","f"};
        MoveDirection[] expectedArray = {MoveDirection.BACKWARD,MoveDirection.FORWARD};

        //when
        MoveDirection[] parsedArray = OptionParser.parseDirection(mixedDirections);

        //then
        assertArrayEquals(expectedArray, parsedArray);
    }

}