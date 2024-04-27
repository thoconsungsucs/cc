package Person;
import ConnectDatabase.ConnectDatabase;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import Room.*;
import Service.Service;

public class Customer extends Person {
    private List<Room> bookedRoom;
    private List<Service> Services;
    private int rate;
    public Customer(int ID, String name, boolean gender, String phone, int rate){
        super(ID, name, gender, phone);
        this.rate = rate;
        bookedRoom = new ArrayList<>();
        Services =  new ArrayList<>();
    }
    public Customer( String name, boolean gender, String phone){

    }
    public List<Room> getBookedRoom(){
        return bookedRoom;
    }
    public List<Service> getServices(){
        return Services;
    }

    public void bookRoom(Room room){
        ConnectDatabase db = new ConnectDatabase();
        Connection conn = db.ConnectDatabase();

        bookedRoom.add(room);
        
    }
    public void bookService(int serviceID){

    }
    public int getRate(){
        return rate;
    }
}

