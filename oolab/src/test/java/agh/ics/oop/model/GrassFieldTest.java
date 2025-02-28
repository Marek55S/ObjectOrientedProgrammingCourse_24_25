package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void grassFieldInit(){
        //when
        GrassField map = new GrassField(5);
        Collection<WorldElement> elements =  map.getElements();
        //then
        assertEquals(5,elements.size());
    }

    @Test
    void firstBasicTest(){
        //given
        GrassField defaultMap = new GrassField(10);
        Animal animal = new Animal();
        //when
        try {
            defaultMap.place(animal);
        }catch (IncorrectPositionException e) {
            fail(e.getMessage() + " exception should not be thrown");}
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
        try {
            defaultMap.place(animal);
        }catch (IncorrectPositionException e) {
            fail(e.getMessage() + " exception should not be thrown");}
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
        try {
            defaultMap.place(animal1);
            defaultMap.place(animal2);
        }catch (IncorrectPositionException e) {
            fail(e.getMessage() + " exception should not be thrown");}
        assertThrows(IncorrectPositionException.class, ()-> defaultMap.place(animal3));
    }

    @Test
    void moveTest(){
        //given
        GrassField defaultMap = new GrassField(5);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(2,3));
        //when
        try {
            defaultMap.place(animal1);
            defaultMap.place(animal2);
        }catch (IncorrectPositionException e) {
            fail(e.getMessage() + " exception should not be thrown");}
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
        Collection<WorldElement> elements = defaultMap.getElements();
        Vector2d grassPosition = new Vector2d(2,2);
        for(WorldElement element : elements){
            if(element instanceof Grass){
                grassPosition = element.getPosition();
                break;
            }
        }
        //when
        try {
            defaultMap.place(animal1);
        } catch (IncorrectPositionException e) {
            fail(e.getMessage() + " exception should not be thrown");
        }
        //then
        assertTrue(defaultMap.isOccupied(new Vector2d(2,2)));
        assertFalse(defaultMap.isOccupied(new Vector2d(10,10)));
        assertTrue(defaultMap.isOccupied(grassPosition));
    }

    @Test
    void objectAtTest(){
        //given
        GrassField defaultMap = new GrassField(5);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(3,3));
        Collection<WorldElement> elements = defaultMap.getElements();
        Vector2d grassPosition = new Vector2d(2,2);
        for(WorldElement element : elements){
            if(element instanceof Grass){
                grassPosition = element.getPosition();
                break;
            }
        }
        Vector2d emptyCell= new Vector2d(10,10);
        //when
        animal2.move(MoveDirection.RIGHT,defaultMap);
        try {
            defaultMap.place(animal1);
            defaultMap.place(animal2);
        } catch (IncorrectPositionException e) {
        fail(e.getMessage() + " exception should not be thrown");}

        //then
        assertEquals(Optional.of(animal1),defaultMap.objectAt(animal1.getPosition()));
        assertEquals(Optional.of(animal2),defaultMap.objectAt(animal2.getPosition()));
        assertNotNull(defaultMap.objectAt(grassPosition));
        assertFalse(defaultMap.objectAt(emptyCell).isPresent());
    }

    @Test
    void getElementsTest(){
        //given
        GrassField defaultMap = new GrassField(5);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(8,8));
        //when
        try {
            defaultMap.place(animal1);
            defaultMap.place(animal2);
        }catch (IncorrectPositionException e) {
            fail(e.getMessage() + " exception should not be thrown");}
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
        try {
            defaultMap.place(animal1);
            defaultMap.place(animal2);
        }catch (IncorrectPositionException e) {
            fail(e.getMessage() + " exception should not be thrown");}
        Boundary firstMapBounds = defaultMap.getCurrentBounds();
        defaultMap.move(animal1,MoveDirection.FORWARD);
        defaultMap.move(animal2,MoveDirection.BACKWARD);
        Boundary secondMapBounds = defaultMap.getCurrentBounds();
        //then
        assertEquals(new Vector2d(7,7),firstMapBounds.UpperRight());
        assertEquals(new Vector2d(0,0),firstMapBounds.LowerLeft());
        assertEquals(new Vector2d(7,8),secondMapBounds.UpperRight());
        assertEquals(new Vector2d(0,-1),secondMapBounds.LowerLeft());

    }

    @Test
    void getOrderedAnimalsTest(){
        //given
        GrassField defaultMap = new GrassField(5);
        Animal animal1 = new Animal(new Vector2d(7,7));
        Animal animal2 = new Animal(new Vector2d(0,0));
        Animal animal3 = new Animal(new Vector2d(0,-1));
        Animal animal4 = new Animal(new Vector2d(3,8));

        List<Animal> inputAnimals = List.of(animal1,animal2,animal3,animal4);
        List<Animal> correcrOrder = List.of(animal3,animal2,animal4,animal1);

        //when
        try {
            for(Animal animal : inputAnimals){
                defaultMap.place(animal);
            }
        }catch (IncorrectPositionException e) {
            fail(e.getMessage() + " exception should not be thrown");}
        Collection<Animal> recievedAnimals = defaultMap.getOrderedAnimals();
        //then
        assertEquals(correcrOrder,recievedAnimals);
        assertNotEquals(inputAnimals,recievedAnimals);
    }


}