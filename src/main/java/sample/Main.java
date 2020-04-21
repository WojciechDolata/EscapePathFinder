package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //primaryStage.setTitle("Hello World");
        //primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.show();
//        System.out.printf("zyje\n");
//
//        int[][] testClauses = {{1, 2, 3}, {-2, 4}};
//        int nVars = 4;
//        int nClauses = 2;
//
//        ISolver solver = SolverFactory.newDefault();
//        solver.newVar(nVars);
//        solver.setExpectedNumberOfClauses(nClauses);
//
//        for(int i=0; i<nClauses; i++){
//            solver.addClause(new VecInt(testClauses[i]));
//        }
//
//        IProblem problem = solver;
//
//        if(problem.isSatisfiable()){
//            System.out.println("Satisfiable!");
//
//            for(int i=1; i<nVars+1; i++) {
//                System.out.println(problem.model(i));
//            }
//        }
//        else{
//            System.out.println("Unsatisfiable!");
//        }

        Building building = new Building();
        building.addRoom(false, false);
        building.addRoom(true, false);
        building.addRoom(false, true);
        building.addConnection(0, 1);
        building.addConnection(1, 2);
        building.print();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
