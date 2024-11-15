package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void grassFieldInit(){
        //when
        GrassField map = new GrassField(5);
        //then
        assertEquals(5,map.grassCount());
    }

    @Test
    void firstBasicTest(){
        //given
        GrassField defaultMap = new GrassField(10);
        Animal animal = new Animal();
        //when
        defaultMap.place(animal);
        defaultMap.move(animal,MoveDirection.FORWARD);
        //then
        assertEquals(new Vector2d(2,3),animal.getPosition());
        assertEquals(MapDirection.NORTH,animal.getOrientation());
    }

    @Test
    void canMoveToTest(){
        GrassField defaultMap = new GrassField(10);
        Vector2d occupiedCell = new Vector2d(2,2);
        Vector2d freeCell = new Vector2d(1,0);
        Animal animal = new Animal();
        //when
        defaultMap.place(animal);
        //then
        assertFalse(defaultMap.canMoveTo(occupiedCell));
        assertTrue(defaultMap.canMoveTo(freeCell));
    }

    @Test
    void placeTest(){
        GrassField defaultMap = new GrassField(5);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(8,8));
        Animal animal3 = new Animal();
        //then
        assertTrue(defaultMap.place(animal1));
        assertTrue(defaultMap.place(animal2));
        assertFalse(defaultMap.place(animal3));
    }

    @Test
    void moveTest(){
        //given
        GrassField defaultMap = new GrassField(5);
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
        GrassField defaultMap = new GrassField(5);
        Animal animal1 = new Animal();
        defaultMap.place(animal1);
        //then
        assertTrue(defaultMap.isOccupied(new Vector2d(2,2)));
        assertFalse(defaultMap.isOccupied(new Vector2d(3,3)));
    }

    // cos nie dziala, zastanow sie jak sprawdzac ta pozal sie boze trawe
    @Test
    void objectAtTest(){
        //given
        GrassField defaultMap = new GrassField(5);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(3,3));
        Vector2d grass = defaultMap.grassPosition();
        Grass expectedGrass = new Grass(defaultMap.grassPosition());
        Vector2d emptyCell= new Vector2d(10,10);
        //when
        animal2.move(MoveDirection.RIGHT,defaultMap);
        defaultMap.place(animal1);
        defaultMap.place(animal2);
        //then
        assertEquals(new Vector2d(2,2),animal1.getPosition());
        assertEquals(new Vector2d(3,3),animal2.getPosition());
        assertNull(defaultMap.objectAt(emptyCell));
        //assertEquals(expectedGrass,defaultMap.objectAt(grass));
    }


}