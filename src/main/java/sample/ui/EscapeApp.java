package sample.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EscapeApp extends Application {

    Building building = BuildingGenerator.getAppBuilding();;
    private EscapeApp escapeApp;
    private Formula formula;
    private EvacSolver evacSolver;

    @FXML
    private Label formulaLabel, area0Label, area1Label, area2Label, area3Label, area4Label, area5Label, area6Label, area7Label, area8Label, area9Label, area10Label, area11Label, area12Label, area13Label, area14Label, area15Label, area16Label, area17Label, area18Label, area19Label, area20Label, area21Label, area22Label, area23Label, area24Label, area25Label, area26Label, area27Label;

    private List<Map<String, String>> allActions = new ArrayList<>();

    private void initSigns(){
        allActions.add(Map.of("1", "→", "S", "S"));
        allActions.add(Map.of("E", "↑"));
        allActions.add(Map.of("1", "←", "S", "S"));
        allActions.add(Map.of("4", "→", "S", "S"));
        allActions.add(Map.of("1", "↑", "5", "→", "3", "←", "6", "↓", "S", "S"));
        allActions.add(Map.of("4", "←", "S", "S"));
        allActions.add(Map.of("4", "↑", "9", "↓", "S", "S"));
        allActions.add(Map.of("10", "↓", "S", "S"));
        allActions.add(Map.of("9", "→", "S", "S"));
        allActions.add(Map.of("6", "↑", "10", "→", "12", "↓", "8", "←", "S", "S"));
        allActions.add(Map.of("9", "←", "18", "→", "7", "↑", "13", "↓", "S", "S"));
        allActions.add(Map.of("8", "↑", "S", "S"));
        allActions.add(Map.of("9", "↑", "S", "S"));
        allActions.add(Map.of("10", "↑", "S", "S"));
        allActions.add(Map.of("18", "↓", "S", "S"));
        allActions.add(Map.of("E", "↑"));
        allActions.add(Map.of("20", "↓", "S", "S"));
        allActions.add(Map.of("21", "↓", "S", "S"));
        allActions.add(Map.of("10", "←", "19", "→", "14", "↑", "23", "↓", "S", "S"));
        allActions.add(Map.of("18", "←", "20", "→", "15", "↑", "24", "↓", "S", "S"));
        allActions.add(Map.of("19", "←", "21", "→", "16", "↑", "25", "↓", "S", "S"));
        allActions.add(Map.of("20", "←", "24", "→", "17", "↑", "26", "↓", "S", "S"));
        allActions.add(Map.of("27", "↓","21", "←", "S", "S"));
        allActions.add(Map.of("18", "↑", "S", "S"));
        allActions.add(Map.of("19", "↑", "S", "S"));
        allActions.add(Map.of("20", "↑", "S", "S"));
        allActions.add(Map.of("21", "↑", "S", "S"));
        allActions.add(Map.of("22", "↑", "S", "S"));
    }

    private void setSigns(){
        int i = 0;

        area0Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
        area1Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
        area2Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
        area3Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
        area4Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
        area5Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
        area6Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
        area7Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
        area8Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
        area9Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
        area10Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
        area11Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
        area12Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
        area13Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
        area14Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
        area15Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
        area16Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
        area17Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
        area18Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
        area19Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
        area20Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
        area21Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
        area22Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
        area23Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
        area24Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
        area25Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
        area26Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
        area27Label.setText(allActions.get(i).get(building.getAreas().get(i).getAction()));
        i+= 1;
    }

    @FXML
    private void generateFormula(ActionEvent event) throws Exception {
        formula = new Formula(building);
        formula.generate();
        formula.print();
        evacSolver = new EvacSolver(formula);
        evacSolver.solve();
        evacSolver.printEvacPlan();
        formulaLabel.setText("generated");
        setSigns();
    }

    public void initialize() {
        initSigns();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getClassLoader().getResource("sample.fxml"));
        Scene scene = new Scene(root, 1000, 600);

        stage.setTitle("Evacuation App");
        stage.setScene(scene);
        stage.show();

        //this.stage = stage;
        //this.stage.setScene(scene);
        //this.stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
