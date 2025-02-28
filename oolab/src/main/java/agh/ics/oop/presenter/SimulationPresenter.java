package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    private WorldMap map;
    public static double CELL_WIDTH = 20;
    public static double CELL_HEIGHT = 30;

    @FXML
    private Label moveDescription;
    @FXML
    private Button startButton;
    @FXML
    private GridPane mapGrid;

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

    private void drawIndices(int minWidth, int minHeight, int mapWidth, int mapHeight) {
        for(int i=0;i<mapWidth;i++){
            Label index = new Label(String.valueOf(i+minWidth));
            mapGrid.add(index,i+1,0);
            GridPane.setHalignment(index, HPos.CENTER);

        }
        for(int i=0;i<=mapHeight;i++){
            Label index = new Label(String.valueOf(mapHeight - i + minHeight));
            mapGrid.add(index,0,i+1);
            GridPane.setHalignment(index, HPos.CENTER);
        }

        Label axisDsc = new Label("y/x");
        mapGrid.add(axisDsc,0,0);
        GridPane.setHalignment(axisDsc, HPos.CENTER);
    }

    private void alignCells(int mapWidth, int mapHeight) {
        for (int i = 0; i <= mapWidth; i++) {
            mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        }
        for (int i = 0; i <= mapHeight+1; i++) {
            mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
        }
    }

    private void drawElements(int minWidth, int minHeight, int mapHeight) {
        for(WorldElement element : map.getElements()){
            Label elem = new Label(element.toString());
            mapGrid.add(elem,element.getPosition().getX()-minWidth+1,mapHeight-(element.getPosition().getY()-minHeight-1));
            GridPane.setHalignment(elem, HPos.CENTER);
        }
    }


    private void drawMap(){
        mapGrid.setAlignment(Pos.CENTER);
        clearGrid();

        int minWidth = map.getCurrentBounds().LowerLeft().getX();
        int minHeight = map.getCurrentBounds().LowerLeft().getY();
        int mapWidth = map.getCurrentBounds().UpperRight().getX()-minWidth+1;
        int mapHeight = map.getCurrentBounds().UpperRight().getY()-minHeight;


        drawIndices(minWidth,minHeight,mapWidth,mapHeight);

        drawElements(minWidth,minHeight,mapHeight);

        alignCells(mapWidth,mapHeight);

    }


    @Override
    public void mapChanged(WorldMap map, String message) {
        Platform.runLater(() -> {
            drawMap();
            moveDescription.setText(message);
        });
    }


    public void onSimulationStartClicked(){
        List<Vector2d> startingPositions = List.of(new Vector2d(0, 0), new Vector2d(10, 1));
        String[] arguments = getArguments();
        Simulation simulation = new Simulation(startingPositions, OptionsParser.parseDirection(arguments), map);
        SimulationEngine engine = new SimulationEngine(List.of(simulation));
        engine.runAsync();
    }


}
