package sample;

public class BuildingGenerator {

    //pamietać że room może być połączony do max. 1 exitu bo sie wypierdala
    public static Building getSmallBuilding(){
        Building building = new Building();
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(false, true);
        building.createConnection(0, 1);
        building.createConnection(0, 2);

        return building;
    }

    public static Building getBigBuilding(){
        Building building = new Building();
        building.addArea(true, false);
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(true, true);
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(false, true);
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
        building.removeArea(4);

        return building;
    }
}
