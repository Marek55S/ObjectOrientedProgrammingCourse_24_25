package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {
    @Test
    public void simulationInit(){
        //given
        List<MoveDirection> moves = List.of();
        WorldMap<Animal, Vector2d> map = new RectangularMap(5,5);
        List<Animal> animals = List.of(new Animal(new Vector2d(0,0)),new Animal(new Vector2d(2,3)));

        //when
        Simulation<Animal, Vector2d> simulation = new Simulation<>(animals,moves,map);
        //then
        assertEquals(2,simulation.getAnimalsList().size());
        assertEquals(new Vector2d(0,0),simulation.getAnimalsList().get(0).getPosition());
        assertEquals(new Vector2d(2,3),simulation.getAnimalsList().get(1).getPosition());
    }

    @Test
    public void simulationSingleAnimal(){
        //given
        List<Animal> animals = List.of(new Animal(new Vector2d(2,2)));
        List<MoveDirection> moves = List.of(MoveDirection.FORWARD,MoveDirection.RIGHT,MoveDirection.BACKWARD);
        WorldMap<Animal, Vector2d> map = new RectangularMap(5,5);
        //when
        Simulation<Animal, Vector2d> simulation = new Simulation<>(animals,moves,map);
        simulation.run();
        //then
        assertEquals(new Vector2d(1,3),simulation.getAnimalsList().getFirst().getPosition());
    }

    @Test
    public void simulationMultiAnimal(){
        //given
        List<Animal> animals = List.of(new Animal(new Vector2d(2,2)),new Animal(new Vector2d(3,4)));
        List<MoveDirection> moves = List.of(
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.RIGHT,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD);
        WorldMap<Animal, Vector2d> map = new RectangularMap(5,5);
        //when
        Simulation<Animal, Vector2d> simulation = new Simulation<>(animals,moves,map);
        simulation.run();
        //then
        assertEquals(new Vector2d(2,2),simulation.getAnimalsList().get(0).getPosition());
        assertEquals(new Vector2d(3,4),simulation.getAnimalsList().get(1).getPosition());
    }
}