package sample;

public class Room {
    private int id;
    private boolean isDanger;
    private boolean isExit;

    public Room(int id, boolean isDanger, boolean isExit) {
        this.id = id;
        this.isDanger = isDanger;
        this.isExit = isExit;
    }

    public void setDanger(boolean danger)
    {
        this.isDanger = danger;
    }

    public void print()
    {
        System.out.println("Room " + id + ": isDanger: " + isDanger + ", isExit: " + isExit);
    }
}
