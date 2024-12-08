package agh.ics.oop;

import agh.ics.oop.model.GrassField;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.presenter.SimulationPresenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SimulationApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane viewRoot = loader.load();
        SimulationPresenter presenter = loader.getController();
        configureStage(primaryStage, viewRoot);

        GrassField map = new GrassField(10);
        List<Vector2d> startingPositions = List.of(new Vector2d(0, 0), new Vector2d(1, 0), new Vector2d(1, 1));
        //String[] arguments = getParameters().getRaw().toArray(new String[0]);
        String[] arguments = new String[]{"f","f","r"};
        List<MoveDirection> directions = OptionsParser.parseDirection(arguments);


        presenter.setWorldMap(map);
        map.addObserver(presenter);
        Simulation simulation = new Simulation(startingPositions, directions, map);
        simulation.run();
        primaryStage.show();
    }

    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }

}
