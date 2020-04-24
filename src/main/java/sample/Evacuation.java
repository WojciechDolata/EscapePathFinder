package sample;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Evacuation {
    private Building building;
    private Map<String, Integer> vars = new HashMap<>();
    private List<int []> clauses = new ArrayList<>();
    private Integer nVars = 1;

    public Evacuation(Building building) {
        this.building = building;
    }

    public void generateFormula(){
        markDanger();
    }

    public void solveFormula() throws Exception{
        int nClauses = clauses.size();

        ISolver solver = SolverFactory.newDefault();
        solver.newVar(nVars);
        solver.setExpectedNumberOfClauses(nClauses);

        for(int[] clause : clauses){
            solver.addClause(new VecInt(clause));
        }

        IProblem problem = solver;

        if(problem.isSatisfiable()){
            System.out.println("Satisfiable!");

            for(int i=1; i<nVars; i++) {
                System.out.println(problem.model(i));
            }
        }
        else{
            System.out.println("Unsatisfiable!");
        }
    }

    private void addClause(int[] varsToAdd){
        clauses.add(varsToAdd);
    }

    private void markDanger(){
        for (Map.Entry<Integer, Room> entry : building.getRooms().entrySet()){
            vars.put("DR"+entry.getValue().getId(), nVars);

            addClause(new int[] {entry.getValue().getDanger()*nVars});

            nVars++;
        }
    }

    public void print(){
        System.out.println("Vars: ");
        System.out.println(vars);

        System.out.println("\nClauses: ");
        for(int[] clause : clauses){
            System.out.print("[");
            for(int var : clause){
                System.out.print(var + " ");
            }
            System.out.print("], ");
        }
        System.out.print('\n');
    }
}
