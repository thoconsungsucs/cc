import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConnectDatabase {
    private String hostName = "localhost:5432";
    private String databaseName = "HMS";
    private String username = "postgres";
    private String password = "123456";
    private String connectionURL = "jdbc:postgresql://" + hostName + "/" + databaseName;

    public Connection connect() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(connectionURL, username, password);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public List<StandardRoom> executeQueryStandardRooms() {
        String query = "SELECT r.room_id, r.price, r.num_of_beds, r.room_type, s.having_shower " +
                "FROM rooms r " +
                "JOIN standard_rooms s ON s.room_id = r.room_id " +
                "WHERE r.is_available = true";
        List<StandardRoom> standardRooms = new ArrayList<>();
        try (Connection con = connect();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
                List<StandardRoom> standardRooms = new ArrayList<>();
            while (rs.next()) {
                standardRooms.add(new StandardRoom(rs.getInt("room_id"), rs.getDouble("price"),
                        rs.getInt("num_of_beds"), rs.getString("room_type"), rs.getBoolean("having_shower")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return standardRooms;
    }

    public List<DeluxeRoom> executeQueryDeluxeRooms() {
        String query = "SELECT r.room_id, r.price, r.num_of_beds, r.room_type, s.furniture " +
                "FROM rooms r " +
                "JOIN standard_rooms s ON s.room_id = r.room_id " +
                "WHERE r.is_available = true";
        List<DeluxeRoom> deluxeRooms = new ArrayList<>();
        try (Connection con = connect();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                deluxeRooms.add(new DeluxeRoom(rs.getInt("room_id"), rs.getDouble("price"),
                        rs.getInt("num_of_beds"), rs.getString("room_type"), rs.getString("furniture")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deluxeRooms;
    }

    public List<SuiteRoom> executeQuerySuiteRooms() {
        String query = "SELECT r.room_id, r.price, r.num_of_beds, r.room_type, s.furniture " +
                "FROM rooms r " +
                "JOIN standard_rooms s ON s.room_id = r.room_id " +
                "WHERE r.is_available = true";
        List<SuiteRoom> suiteRooms = new ArrayList<>();
        try (Connection con = connect();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                suiteRooms.add(new SuiteRoom(rs.getInt("room_id"), rs.getDouble("price"),
                        rs.getInt("num_of_beds"), rs.getString("room_type"), rs.getString("furniture")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return suiteRooms;
    }

    public List<Room> executeQueryRooms() {
        List<Room> rooms = new ArrayList<>();
        rooms.addAll(executeQueryStandardRooms());
        rooms.addAll(executeQueryDeluxeRooms());
        rooms.addAll(executeQuerySuiteRooms());
        return rooms;
    }
}
