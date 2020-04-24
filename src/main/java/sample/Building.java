package sample;

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

public class Building {
    private Map<Integer, Room> rooms = new HashMap<>();
    private Map<Integer, List<Integer>> neighbours = new HashMap<>();
    private Integer buildingSize = 0;

    public Building(){
    }

    public Map<Integer, List<Integer>> getNeighbours(){
        return neighbours;
    }

    public Map<Integer, Room> getRooms(){
        return rooms;
    }

    public void addRoom(boolean isDanger, boolean isExit){
        rooms.put(buildingSize, new Room(buildingSize, isDanger, isExit));
        buildingSize++;
    }

    public void updateRoom(Integer id, boolean isDanger, boolean isExit){
        rooms.get(id).setDanger(isDanger);
        rooms.get(id).setExit(isExit);
    }

    public void removeRoom(Integer id){
        rooms.remove(id);
        neighbours.remove(id);

        for (Map.Entry<Integer, List<Integer>> entry : neighbours.entrySet()) {
            Integer idx = entry.getValue().indexOf(id);

            if(idx != -1){
                List<Integer> updated = entry.getValue();
                updated.remove(entry.getValue().indexOf(id));
                neighbours.put(entry.getKey(), updated);
            }
        }
    }

    private void updateConnections(Integer room1, Integer room2){
        List<Integer> updated;
        if(neighbours.containsKey(room1)){
            updated = neighbours.get(room1);
        }
        else{
            updated = new ArrayList<>();
        }
        updated.add(room2);
        neighbours.put(room1, updated);
    }

    private void updateRemoveConnections(Integer room1, Integer room2){
        List<Integer> updated;

        updated = neighbours.get(room1);
        updated.remove(neighbours.get(room1).indexOf(room2));
        neighbours.put(room1, updated);
        }


    public void createConnection(Integer room1, Integer room2){
        updateConnections(room1, room2);
        updateConnections(room2, room1);
    }

    public void removeConnection(Integer room1, Integer room2){
        updateRemoveConnections(room1, room2);
        updateRemoveConnections(room2, room1);
    }

    public void print(){
        for (Map.Entry<Integer, Room> entry : rooms.entrySet())
        {
            entry.getValue().print();
        }

        for (Map.Entry<Integer, List<Integer>> entry : neighbours.entrySet()) {
            System.out.println("Room " + entry.getKey() + " is neighbours with " + entry.getValue());
        }

        System.out.println("\n");

    }
}
