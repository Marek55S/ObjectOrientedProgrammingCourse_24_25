package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine {
    private final List<Simulation> simulationsList;
    private final List<Thread> threadList;

    public SimulationEngine(List<Simulation> simulations) {
        simulationsList = simulations;
        threadList = new ArrayList<Thread>();
        for(Simulation simulation : simulationsList) {
            threadList.add(new Thread(simulation));
        }
    }

    public void runSync(){
        for(Simulation simulation : simulationsList){
            simulation.run();
        }
    }

    public void runAsync(){
        for(int i=0;i<simulationsList.size();i++){
            threadList.get(i).start();
        }
    }

    public void awaitSimulationsEnd(){

    }

}
