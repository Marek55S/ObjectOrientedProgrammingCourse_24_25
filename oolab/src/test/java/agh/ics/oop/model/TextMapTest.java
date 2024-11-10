package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextMapTest {

    @Test
    void initTextMap() {
        //when
        WorldNumberPositionMap<String,Integer> map = new TextMap<>();
        List<String> emptyList = new ArrayList<String>();
        //then
        assertEquals(emptyList.toString(), map.toString());
    }

    @Test
    void PlaceTest(){
        //given
        List<String> texts = List.of("Ala", "ma", "sowoniedźwiedzia");
        WorldMap<String,Integer> map = new TextMap<>();
        //when
        for (String text : texts) {
            map.place(text);
        }
        //then
        assertEquals(texts.toString(), map.toString());
    }

    @Test
    void canMoveToTest(){
        //given
        WorldNumberPositionMap<String,Integer> map = new TextMap<>();
        List<String> texts = List.of("Ala", "ma", "sowoniedźwiedzia");
        //when
        for (String text : texts) {
            map.place(text);
        }
        //then
        assertFalse(map.canMoveTo(-1));
        assertFalse(map.canMoveTo(4));
        assertTrue(map.canMoveTo(1));
    }

    @Test
    void isOccupiedTest(){
        //given
        WorldNumberPositionMap<String,Integer> map = new TextMap<>();
        List<String> texts = List.of("Ala", "ma", "sowoniedźwiedzia");
        //when
        for (String text : texts) {
            map.place(text);
        }
        //then
        assertFalse(map.isOccupied(-1));
        assertTrue(map.isOccupied(1));
        assertFalse(map.isOccupied(3));
    }

    @Test
    void objectAtTest(){
        //given
        WorldNumberPositionMap<String,Integer> map = new TextMap<>();
        List<String> texts = List.of("Ala", "ma", "sowoniedźwiedzia");
        //when
        for (String text : texts) {
            map.place(text);
        }
        //then
        assertEquals("ma",texts.get(1));
        assertEquals("Ala",texts.get(0));
    }

    @Test
    void moveForwardRightTest(){
        //given
        WorldNumberPositionMap<String,Integer> map = new TextMap<>();
        List<String> texts = List.of("a", "b", "c", "d", "e");
        for (String text : texts) {
            map.place(text);
        }
        List<String> expectedList = List.of("b", "c", "a", "d", "e");
        //when
        map.move("a",MoveDirection.RIGHT);
        map.move("a",MoveDirection.FORWARD);
        map.move("e",MoveDirection.RIGHT);
        //then
        assertEquals(expectedList.toString(), map.toString());
    }

    @Test
    void moveBackwardLeftTest(){
        //given
        WorldNumberPositionMap<String,Integer> map = new TextMap<>();
        List<String> texts = List.of("a", "b", "c", "d", "e");
        for (String text : texts) {
            map.place(text);
        }
        List<String> expectedList = List.of("a", "b", "d", "e", "c");
        //when
        map.move("a",MoveDirection.LEFT);
        map.move("d",MoveDirection.BACKWARD);
        map.move("e",MoveDirection.LEFT);
        //then
        assertEquals(expectedList.toString(), map.toString());
    }

}