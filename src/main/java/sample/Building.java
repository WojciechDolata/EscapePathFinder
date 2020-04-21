package sample;

import java.util.*;

public class Building {
    private List<Room> rooms = new ArrayList<>();
    private Map<Integer, List<Integer>> neighbours = new HashMap<>();

    public Building(){
    }

    public void addRoom(boolean isDanger, boolean isExit){
        rooms.add(new Room(rooms.size(), isDanger, isExit));
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

    public void addConnection(Integer room1, Integer room2){
        updateConnections(room1, room2);
        updateConnections(room2, room1);
    }

    public void print(){
        for (Room room : rooms)
        {
            room.print();
        }
        System.out.println("\n");

        for (Map.Entry<Integer, List<Integer>> entry : neighbours.entrySet()) {
            System.out.println("Room " + entry.getKey() + " is neighbours with " + entry.getValue());
        }

    }
}
