package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void firstBasicTest(){
        //given
        RectangularMap defaultMap = new RectangularMap(5,5);
        Animal animal = new Animal();
        //when
        defaultMap.place(animal);
        defaultMap.move(animal,MoveDirection.FORWARD);
        //then
        assertEquals(new Vector2d(2,3),animal.getPosition());
        assertEquals(MapDirection.NORTH,animal.getOrientation());
    }

    @Test
    void CanMoveToTest(){
        //given
        RectangularMap defaultMap = new RectangularMap(5,5);
        Vector2d offTheMap = new Vector2d(7,8);
        Vector2d occupiedCell = new Vector2d(2,2);
        Vector2d freeCell = new Vector2d(1,0);
        Animal animal = new Animal();
        //when
        defaultMap.place(animal);
        //then
        assertFalse(defaultMap.canMoveTo(offTheMap));
        assertFalse(defaultMap.canMoveTo(occupiedCell));
        assertTrue(defaultMap.canMoveTo(freeCell));
    }

    @Test
    void placeTest(){
        //given
        RectangularMap defaultMap = new RectangularMap(5,5);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(3,3));
        Animal animal3 = new Animal(new Vector2d(8,8));
        Animal animal4 = new Animal();
        //then
        assertTrue(defaultMap.place(animal1));
        assertTrue(defaultMap.place(animal2));
        assertFalse(defaultMap.place(animal3));
        assertFalse(defaultMap.place(animal4));
    }

    @Test
    void moveTest(){
        //given
        RectangularMap defaultMap = new RectangularMap(5,5);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(2,3));
        //when
        defaultMap.place(animal1);
        defaultMap.place(animal2);
        defaultMap.move(animal1,MoveDirection.FORWARD);
        defaultMap.move(animal1,MoveDirection.RIGHT);
        defaultMap.move(animal2,MoveDirection.LEFT);
        defaultMap.move(animal2,MoveDirection.FORWARD);
        //then
        assertEquals(new Vector2d(2,2),animal1.getPosition());
        assertEquals(MapDirection.EAST,animal1.getOrientation());
        assertEquals(new Vector2d(1,3),animal2.getPosition());
        assertEquals(MapDirection.WEST,animal2.getOrientation());
    }

    @Test
    void isOccupiedTest(){
        //given
        RectangularMap defaultMap = new RectangularMap(5,5);
        Animal animal1 = new Animal();
        //when
        defaultMap.place(animal1);
        //then
        assertTrue(defaultMap.isOccupied(new Vector2d(2,2)));
        assertFalse(defaultMap.isOccupied(new Vector2d(3,3)));
    }

    @Test
    void objectAtTest(){
        //given
        RectangularMap defaultMap = new RectangularMap(5,5);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(3,3));
        //when
        animal2.move(MoveDirection.RIGHT,defaultMap);
        defaultMap.place(animal1);
        defaultMap.place(animal2);
        //then
        assertEquals(new Vector2d(2,2),animal1.getPosition());
        assertEquals(new Vector2d(3,3),animal2.getPosition());
    }

    //ufam, że MapVisualizer działa poprawnie

    @Test
    void isInMapBoundsTest(){
        //given
        RectangularMap otherMap = new RectangularMap(10,5);
        //then
        assertTrue(otherMap.isInMapBounds(new Vector2d(2,2)));
        assertFalse(otherMap.isInMapBounds(new Vector2d(11,-2)));
        assertFalse(otherMap.isInMapBounds(new Vector2d(7,7)));
    }
}