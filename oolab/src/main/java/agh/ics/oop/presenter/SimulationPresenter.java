package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    public WorldMap map;
    public Label moveDescription;
    public Button startButton;
    public GridPane mapGrid;
    public static double CELL_WIDTH = 20;
    public static double CELL_HEIGHT = 30;

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

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().getFirst()); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    private void drawIndexes(){
        int minWidth = map.getCurrentBounds().LowerLeft().getX()-1;
        int minHeight = map.getCurrentBounds().LowerLeft().getY()-1;
        int mapWidth = map.getCurrentBounds().UpperRight().getX()-minWidth;
        int mapHeight = map.getCurrentBounds().UpperRight().getY()-minHeight;
        for(int i=minWidth+1;i<mapWidth;i++) mapGrid.add(new Label(String.valueOf(i)),i,minWidth);
        for(int i=minHeight+1;i<mapHeight;i++) mapGrid.add(new Label(String.valueOf(i)),minHeight,i);
        mapGrid.add(new Label("y/x"),minWidth,minHeight);


    }


    private void drawElements(){
        for(WorldElement element : map.getElements()){
            mapGrid.add(new Label(element.toString()),element.getPosition().getX(),element.getPosition().getY());
        }
    }

    private void drawMap(){
        clearGrid();
        mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
    }


    @Override
    public void mapChanged(WorldMap map, String message) {
        Platform.runLater(() -> {
            drawMap();
            moveDescription.setText(message);
        });
    }


    public void onSimulationStartClicked(){
        List<Vector2d> startingPositions = List.of(new Vector2d(0, 0), new Vector2d(1, 1));
        String[] arguments = getArguments();
        Simulation simulation = new Simulation(startingPositions, OptionsParser.parseDirection(arguments), map);
        SimulationEngine engine = new SimulationEngine(List.of(simulation));
        engine.runAsync();
    }


}
