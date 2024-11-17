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
        Vector2d grassPosition = defaultMap.getGrassPosition();
        //when
        defaultMap.place(animal1);
        //then
        assertTrue(defaultMap.isOccupied(new Vector2d(2,2)));
        assertFalse(defaultMap.isOccupied(new Vector2d(10,10)));
        assertTrue(defaultMap.isOccupied(grassPosition));
    }

    // cos nie dziala, zastanow sie jak sprawdzac ta pozal sie boze trawe
    @Test
    void objectAtTest(){
        //given
        GrassField defaultMap = new GrassField(5);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(3,3));
        Vector2d grassPosition = defaultMap.getGrassPosition();
        Vector2d emptyCell= new Vector2d(10,10);
        //when
        animal2.move(MoveDirection.RIGHT,defaultMap);
        defaultMap.place(animal1);
        defaultMap.place(animal2);
        //then
        assertEquals(animal1,defaultMap.objectAt(animal1.getPosition()));
        assertEquals(animal2,defaultMap.objectAt(animal2.getPosition()));
        assertNotNull(defaultMap.objectAt(grassPosition));
        assertNull(defaultMap.objectAt(emptyCell));
    }

    @Test
    void getGrassPositionTest(){
        //given
        GrassField defaultMap = new GrassField(5);
        Vector2d grassPosition = defaultMap.getGrassPosition();
        //then
        assertEquals(grassPosition,defaultMap.getGrassPosition());
    }

    @Test
    void getElementsTest(){
        //given
        GrassField defaultMap = new GrassField(5);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(8,8));
        //when
        defaultMap.place(animal1);
        defaultMap.place(animal2);
        //then
        assertEquals(7,defaultMap.getElements().size());
    }

    @Test
    void mapBoundsTest(){
        //given
        GrassField defaultMap = new GrassField(5);
        Animal animal1 = new Animal(new Vector2d(7,7));
        Animal animal2 = new Animal(new Vector2d(0,0));
        //when
        defaultMap.place(animal1);
        defaultMap.place(animal2);
        Vector2d lower = defaultMap.getLowerLeft();
        Vector2d upper = defaultMap.getUpperRight();
        defaultMap.move(animal1,MoveDirection.FORWARD);
        defaultMap.move(animal2,MoveDirection.BACKWARD);
        Vector2d lower2 = defaultMap.getLowerLeft();
        Vector2d upper2 = defaultMap.getUpperRight();
        //then
        assertEquals(new Vector2d(7,7),upper);
        assertEquals(new Vector2d(0,0),lower);
        assertEquals(new Vector2d(7,8),upper2);
        assertEquals(new Vector2d(0,-1),lower2);

    }


}