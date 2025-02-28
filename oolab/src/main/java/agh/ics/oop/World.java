package agh.ics.oop;

import agh.ics.oop.model.*;
import javafx.application.Application;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class World {
    public static void main(String[] args) {

        try {
            List<MoveDirection> directions = OptionsParser.parseDirection(args);
            List<Vector2d> positions = List.of(new Vector2d(1, 1), new Vector2d(3, 1));
            AbstractWorldMap map1 = new GrassField(10);
            AbstractWorldMap map2 = new RectangularMap(7, 7);
//            map1.addObserver(new ConsoleMapDisplay());
//            map2.addObserver(new ConsoleMapDisplay());
            map1.addObserver((map,message) -> System.out.printf("%s %s%n", LocalTime.now(),message));
            Simulation simulation1 = new Simulation(positions, directions, map1);
//            Simulation simulation2 = new Simulation(positions, directions, map2);
            simulation1.run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("system zakończył działanie");
        //Application.launch(SimulationApp.class,args);

    }

}
