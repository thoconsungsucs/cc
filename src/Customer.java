import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Customer extends Person{
    private List<Integer> bookedRoom;
    private List<Integer> Services;
    private int rate;
    public Customer(int ID, String name, boolean gender, String phone,int rate){
        super(ID, name, gender, phone);
        this.rate = rate;
        bookedRoom = new ArrayList<>();
        Services =  new ArrayList<>();
    }

    public void bookRoom(int roomID){
        ConnectDatabase db = new ConnectDatabase();
        Connection conn = db.ConnectDatabase();
        Statement stmt = conn.prepareStatement("");
        bookedRoom.add(roomID);
        
    }
    public void bookService(int serviceID){
        Services.add(serviceID);
    }
    public int getRate(){
        return rate;
    }
    public List<Integer> getBookedRoom(){
        //Tìm trong db và in ra danh sách room
        return bookedRoom;
    }
    public List<Integer> getServices(){
        //Tìm trong db và in ra danh sách services
        return Services;
    }
}

