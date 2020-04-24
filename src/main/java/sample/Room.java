package sample;

public class Room {
    private Integer id;
    private boolean isDanger;
    private boolean isExit;

    public Room(int id, boolean isDanger, boolean isExit) {
        this.id = id;
        this.isDanger = isDanger;
        this.isExit = isExit;
    }

    public Integer getId(){
        return id;
    }

    public Integer getDanger(){
        if(isDanger) {
            return 1;
        }
        return -1;
    }

    public boolean getExit(){
        return isExit;
    }

    public void setDanger(boolean danger)
    {
        this.isDanger = danger;
    }

    public void setExit(boolean exit)
    {
        this.isExit = exit;
    }

    public void print()
    {
        System.out.println("Room " + id + ": isDanger: " + isDanger + ", isExit: " + isExit);
    }
}
