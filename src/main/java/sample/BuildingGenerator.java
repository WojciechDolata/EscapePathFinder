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

    public static Building getAppBuilding(){
        Building building = new Building();
        building.addArea(false, false);
        building.addArea(false, true);
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(false, true);
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(false, false);
        building.addArea(false, false);

        building.createConnection(0, 1);
        building.createConnection(3, 4);
        building.createConnection(2, 1);
        building.createConnection(5, 4);
        building.createConnection(4, 1);
        building.createConnection(4, 6);
        building.createConnection(8, 11);
        building.createConnection(8, 9);
        building.createConnection(9, 6);
        building.createConnection(9, 12);
        building.createConnection(9, 10);
        building.createConnection(10, 7);
        building.createConnection(10, 13);
        building.createConnection(10, 18);
        building.createConnection(18, 23);
        building.createConnection(18, 14);
        building.createConnection(18, 19);
        building.createConnection(15, 19);
        building.createConnection(24, 19);
        building.createConnection(19, 20);
        building.createConnection(16, 20);
        building.createConnection(25, 20);
        building.createConnection(21, 20);
        building.createConnection(17, 21);
        building.createConnection(26, 21);
        building.createConnection(22, 21);
        building.createConnection(27, 22);

        return building;
    }
}
