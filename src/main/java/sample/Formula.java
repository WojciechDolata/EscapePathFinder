package sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Formula {
    private Building building;
    private Map<String, Integer> vars = new HashMap<>();
    private Map<Integer, String> reversedVars = new HashMap<>();
    private List<int []> clauses = new ArrayList<>();
    private Integer nVars = 1;


    public Formula(Building building) {
        this.building = building;
    }

    public Building getBuilding(){
        return building;
    }

    public Map<String, Integer> getVars(){
        return vars;
    }

    public Map<Integer, String> getReversedVars(){
        return reversedVars;
    }

    public List<int[]> getClauses(){
        return clauses;
    }

    public Integer getNVars(){
        return nVars;
    }

    public void generate(){
        markDanger();
        checkNeighbours();
        checkConnection();
        preferMoving();
        onlyOneChoice();
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

    public String getVarNameMove(Integer room1, Integer room2){
        return "R"+room1+"_M_R"+room2;
    }

    private  String getVarNameConn(Integer room) {
        return "C_R" + room;
    }

    public  String getVarNameStay(Integer room) {
        return "S_R" + room;
    }

    private void markDanger(){
        for (Map.Entry<Integer, Room> entry : building.getRooms().entrySet()){
            Integer newVar = addVar(getVarNameDanger(entry.getValue().getId()));
            addClause(new int[] {entry.getValue().getDanger()*newVar});
        }
    }

    private void checkNeighbours(){
        for (Map.Entry<Integer, Room> entry : building.getRooms().entrySet()){
            if(entry.getValue().getExit()){
                continue;
            }

            for(Integer neighId : building.getNeighbours().get(entry.getKey())){
                Integer roomId = entry.getValue().getId();
                Room neighbour = building.getRooms().get(neighId);

                if(neighbour.getExit()){
                    Integer newVar = addVar(getVarNameMove(roomId, neighId));
                    addClause(new int[] {newVar, vars.get(getVarNameDanger(neighId))});
                    addClause(new int[] {-newVar, -vars.get(getVarNameDanger(neighId))});
                }
                else{
                    addVar(getVarNameMove(roomId, neighId));
                    addVar(getVarNameMove(neighId, roomId));
                    addVar(getVarNameConn(neighId));
                    addClause(new int[] {-vars.get(getVarNameMove(roomId, neighId)), -vars.get(getVarNameDanger(neighId))});
                    addClause(new int[] {-vars.get(getVarNameMove(roomId, neighId)), -vars.get(getVarNameMove(neighId, roomId))});
                    addClause(new int[] {-vars.get(getVarNameMove(roomId, neighId)), vars.get(getVarNameConn(neighId))});
                }
            }
        }
    }

    private void checkConnection(){
        for (Map.Entry<Integer, Room> entry : building.getRooms().entrySet()) {
            if(entry.getValue().getExit()){
                continue;
            }

            List<int[]> tmpClauses = new ArrayList<>();

            for (Integer neighId : building.getNeighbours().get(entry.getKey())) {
                Integer roomId = entry.getValue().getId();
                Room neighbour = building.getRooms().get(neighId);

                addVar(getVarNameConn(neighbour.getId()));
                addVar(getVarNameConn(roomId));
                if(neighbour.getExit()){
                    tmpClauses.add(new int[] {vars.get(getVarNameMove(roomId, neighId))});
                    addClause(new int[] {-vars.get(getVarNameMove(roomId, neighId)), vars.get(getVarNameConn(roomId))});
                }
                else{
                    tmpClauses.add(new int[] {vars.get(getVarNameMove(roomId, neighId)), vars.get(getVarNameConn(neighId))});
                    addClause(new int[] {-vars.get(getVarNameMove(roomId, neighId)), -vars.get(getVarNameConn(neighId)), vars.get(getVarNameConn(roomId))});
                }
            }

            tmpClauses.add(new int[] {-vars.get(getVarNameConn(entry.getValue().getId()))});
            int iterations = 1;

            for(int i = 0; i<tmpClauses.size(); i++){
                iterations *= tmpClauses.get(i).length;
            }

            for(int i = 0; i<iterations; i++){
                List<Integer> tmpClauseToAdd = new ArrayList<>();
                int j = 1;

                for(int[] clause : tmpClauses){
                    tmpClauseToAdd.add(clause[(i/j)%clause.length]);
                    j *= clause.length;

                }
                addClause(tmpClauseToAdd.stream().mapToInt(x->x).toArray());
            }
        }
    }

    private void preferMoving(){
        for (Map.Entry<Integer, Room> entry : building.getRooms().entrySet()) {
            if(entry.getValue().getExit()){
                continue;
            }

            for (Integer neighId : building.getNeighbours().get(entry.getKey())) {
                Integer roomId = entry.getValue().getId();
                Room neighbour = building.getRooms().get(neighId);

                addVar(getVarNameStay(roomId));
                if(neighbour.getExit()){
                    addClause(new int[] {-vars.get(getVarNameStay(roomId)), vars.get(getVarNameDanger(neighId))});
                }
                else{
                    addClause(new int[] {-vars.get(getVarNameStay(roomId)), vars.get(getVarNameDanger(neighId)), -vars.get(getVarNameConn(neighId))});
                }
            }
        }
    }

    private void onlyOneChoice() {
        for (Map.Entry<Integer, Room> entry : building.getRooms().entrySet()) {
            if (entry.getValue().getExit()) {
                continue;
            }

            Integer roomId = entry.getValue().getId();
            List<Integer> lastFormula = new ArrayList<>();
            lastFormula.add(vars.get(getVarNameStay(roomId)));

            for (int i = 0; i < building.getNeighbours().get(entry.getKey()).size(); i++) {
                Integer neighId = building.getNeighbours().get(entry.getKey()).get(i);
                for (int j = i + 1; j < building.getNeighbours().get(entry.getKey()).size(); j++) {
                    Integer neigh2Id = building.getNeighbours().get(entry.getKey()).get(j);
                    if (neighId != neigh2Id) {
                        addClause(new int[]{-vars.get(getVarNameMove(roomId, neighId)), -vars.get(getVarNameMove(roomId, neigh2Id))});
                    }
                    addClause(new int[]{-vars.get(getVarNameMove(roomId, neighId)), -vars.get(getVarNameStay(roomId))});
                }
                lastFormula.add(vars.get(getVarNameMove(roomId, neighId)));
            }
            addClause(lastFormula.stream().mapToInt(x -> x).toArray());
        }
    }

    public void print(){
        //System.out.println("Vars: ");
        //System.out.println(vars);

        System.out.println("\nFormula: ");
        for(int j=0; j<clauses.size(); j++){
            int[] clause = clauses.get(j);

            if(j != 0){
                System.out.print("\u2227 (");
            }
            else{
                System.out.print("(");
            }

            for(int i=0; i < clause.length; i++){
                if(clause[i] < 0){
                    System.out.print("~");
                }
                if(i != clause.length-1){
                    System.out.print(reversedVars.get(java.lang.Math.abs(clause[i])) + " \u2228 ");
                }
                else{
                    System.out.print(reversedVars.get(java.lang.Math.abs(clause[i])));
                }

            }
            System.out.print(")\n");
        }
        System.out.print('\n');
    }
}
