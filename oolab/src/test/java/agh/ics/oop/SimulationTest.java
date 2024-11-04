package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {
    @Test
    public void simulationInit(){
        //given
        List<Vector2d> startingPositions = List.of(new Vector2d(0,0),new Vector2d(2,3));
        List<MoveDirection> moves = List.of();
        //when
        Simulation simulation = new Simulation(startingPositions,moves);
        //then
        assertEquals(2,simulation.getAnimalsList().size());
        assertEquals(new Vector2d(0,0),simulation.getAnimalsList().get(0).getPosition());
        assertEquals(new Vector2d(2,3),simulation.getAnimalsList().get(1).getPosition());
    }

    @Test
    public void simulationSingleAnimal(){
        //given
        List<Vector2d> startingPositions = List.of(new Vector2d(2,2));
        List<MoveDirection> moves = List.of(MoveDirection.FORWARD,MoveDirection.RIGHT,MoveDirection.BACKWARD);
        //when
        Simulation simulation = new Simulation(startingPositions,moves);
        simulation.run();
        //then
        assertEquals(new Vector2d(1,3),simulation.getAnimalsList().get(0).getPosition());
    }

    @Test
    public void simulationMultiAnimal(){
        //given
        List<Vector2d> startingPositions = List.of(new Vector2d(2,2),new Vector2d(3,4));
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
        //when
        Simulation simulation = new Simulation(startingPositions,moves);
        simulation.run();
        //then
        assertEquals(new Vector2d(3,2),simulation.getAnimalsList().get(0).getPosition());
        assertEquals(new Vector2d(2,4),simulation.getAnimalsList().get(1).getPosition());
    }
}