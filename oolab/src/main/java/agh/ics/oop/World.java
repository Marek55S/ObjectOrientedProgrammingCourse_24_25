package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

public class World {
    public static void main(String[] args) {
        try {
            List<MoveDirection> directions = OptionsParser.parseDirection(args);
            List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4), new Vector2d(2,2));
            AbstractWorldMap map1 = new GrassField(10);
            AbstractWorldMap map2 = new RectangularMap(7,7);
            map1.addObserver(new ConsoleMapDisplay());
            map2.addObserver(new ConsoleMapDisplay());
            List<Simulation> simulations = List.of(new Simulation(positions, directions,map1), new Simulation(positions, directions,map2));
            SimulationEngine engine = new SimulationEngine(simulations);
            engine.runSync();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}
