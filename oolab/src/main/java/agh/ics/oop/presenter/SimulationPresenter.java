package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationApp;
import agh.ics.oop.model.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    public WorldMap map;
    public Label moveDescription;
    public Button startButton;

    @FXML
    private Label infoLabel;
    @FXML
    private TextField arguments;

    public void setWorldMap(WorldMap map) {
        this.map = map;
    }

    private String[] getArguments(){
        return arguments.getText().split(" ");
    }

    private void drawMap(){
        infoLabel.setText(map.toString());
    }

    @Override
    public void mapChanged(WorldMap map, String message) {
        drawMap();
    }

    //zastanow sie czy symulacja ma dodawac caly czas zwierzeta na mape/ ew jak to zmienic
    public void onSimulationStartClicked(){
        List<Vector2d> startingPositions = List.of(new Vector2d(0, 0), new Vector2d(1, 0), new Vector2d(1, 1));
        String[] arguments = getArguments();
        List<MoveDirection> directions = OptionsParser.parseDirection(arguments);
        //map.addObserver(presenter);
        Simulation simulation = new Simulation(startingPositions, directions, map);
        simulation.run();
    }


}
