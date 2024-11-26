package agh.ics.oop;

import java.util.List;

public class SimulationEngine {
    private final List<Simulation> simulationsList;

    public SimulationEngine(List<Simulation> simulations) {
        simulationsList = simulations;
    }

    public void runSync(){
        for(Simulation simulation : simulationsList){
            simulation.run();
        }
    }
}
