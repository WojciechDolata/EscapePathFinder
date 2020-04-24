package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //primaryStage.setTitle("Hello World");
        //primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.show();

        Building building = new Building();
        building.addRoom(true, false);
        building.addRoom(false, false);
        building.addRoom(false, false);
        building.addRoom(false, false);
        building.addRoom(false, true);
        building.addRoom(false, false);
        building.addRoom(false, false);
        building.addRoom(false, false);
        building.addRoom(false, false);
        building.addRoom(false, true);
        building.createConnection(0, 1);
        building.createConnection(0, 3);
        building.createConnection(1, 3);
        building.createConnection(2, 3);
        building.createConnection(5, 6);
        building.createConnection(5, 3);
        building.createConnection(6, 3);
        building.createConnection(7, 3);
        building.createConnection(7, 8);
        building.createConnection(3, 9);
        building.createConnection(3, 4);
        building.print();

        Evacuation plan = new Evacuation(building);
        plan.generateFormula();
        plan.print();
        plan.solveFormula();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
