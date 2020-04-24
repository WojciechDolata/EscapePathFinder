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
    private Map<Integer, String> reversedVars = new HashMap<>();
    private List<int []> clauses = new ArrayList<>();
    private Integer nVars = 1;

    public Evacuation(Building building) {
        this.building = building;
    }

    public void generateFormula(){
        markDanger();
        checkNeighbours();
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
                System.out.println(reversedVars.get(i) + ": " + problem.model(i));
            }
        }
        else{
            System.out.println("Unsatisfiable!");
        }
    }

    private boolean isVarInitialized(String varName){
        return vars.get(varName) != null;
    }

    private void addClause(int[] varsToAdd){
        clauses.add(varsToAdd);
    }

    private Integer addVar(String varName){
        if(!isVarInitialized(varName)){
            vars.put(varName, nVars);
            reversedVars.put(nVars, varName);
            nVars++;

            return nVars-1;
        }
        return null;
    }

    private String getVarNameDanger(Integer room){
        return "D_R"+room;
    }

    private String getVarNameMove(Integer room1, Integer room2){
        return "R"+room1+"_M_R"+room2;
    }

    private  String getVarNameConn(Integer room) {
        return "C_R" + room;
    }

    private void markDanger(){
        for (Map.Entry<Integer, Room> entry : building.getRooms().entrySet()){
            Integer newVar = addVar(getVarNameDanger(entry.getValue().getId()));
            addClause(new int[] {entry.getValue().getDanger()*newVar});
        }
    }

    private void checkNeighbours(){
        for (Map.Entry<Integer, Room> entry : building.getRooms().entrySet()){
            for(Integer neighId : building.getNeighbours().get(entry.getKey())){
                Room room = entry.getValue();
                Room neighbour = building.getRooms().get(neighId);

                if(neighbour.getExit()){
                    Integer newVar = addVar(getVarNameMove(room.getId(), neighbour.getId()));
                    addClause(new int[] {newVar, vars.get(getVarNameDanger(neighbour.getId()))});
                    addClause(new int[] {-newVar, -vars.get(getVarNameDanger(neighbour.getId()))});
                }
                else{
                    addVar(getVarNameMove(room.getId(), neighbour.getId()));
                    addVar(getVarNameMove(neighbour.getId(), room.getId()));
                    addVar(getVarNameConn(neighbour.getId()));
                    addClause(new int[] {-vars.get(getVarNameMove(room.getId(), neighbour.getId())), -vars.get(getVarNameDanger(neighbour.getId()))});
                    addClause(new int[] {-vars.get(getVarNameMove(room.getId(), neighbour.getId())), -vars.get(getVarNameMove(neighbour.getId(), room.getId()))});
                    addClause(new int[] {-vars.get(getVarNameMove(room.getId(), neighbour.getId())), vars.get(getVarNameConn(neighbour.getId()))});
                }
            }
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
