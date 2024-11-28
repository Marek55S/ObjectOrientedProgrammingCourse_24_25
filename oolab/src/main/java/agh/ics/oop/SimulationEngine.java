package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {
    private final List<Simulation> simulationsList;
    private final List<Thread> threadList = new ArrayList<>();
    private final ExecutorService threadPool = Executors.newFixedThreadPool(4);

    public SimulationEngine(List<Simulation> simulations) {
        simulationsList = simulations;
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
        this.awaitSimulationsEnd();
    }

    private void awaitSimulationsEnd(){
        for(int i=0;i<simulationsList.size();i++){
            try {
                threadList.get(i).join();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println("wątek został przerwany");
            }
        }
    }

    private void awaitSimulationsEndForThreadPool(){
        try{
            if(!threadPool.awaitTermination(60, TimeUnit.SECONDS)){
                System.out.println("Wątki zostały zakończone przed wykonaniem wszystkich zadań");
                threadPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void runAsyncInThreadPool(){
        for(Simulation simulation: simulationsList){
            threadPool.submit(simulation);
        }
        threadPool.shutdown();
        this.awaitSimulationsEndForThreadPool();
    }

}
