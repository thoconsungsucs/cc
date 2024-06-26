import java.security.Provider.Service;
import java.util.List;
import java.util.Scanner;

public class Hotel {
    private String name;
    private String address;
    List<Room> rooms;
    List<Customer> customers;
    List<Employee> employees;
    List<Service> services;
    List<Service> tasks;

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public List<Customer> getCustomers() {
        return this.customers;
    }

    public List<Employee> getEmployees() {
        return this.employees;
    }

    public List<Service> getServices() {
        return this.services;
    }

    public void printRooms() {
        System.out.println("Rooms in hotel:");
        rooms.forEach(r -> System.out.println(r.toString()));
    }

    public void booking() {

        // Add customer
        System.out.println("Add customer:");
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter customer name: ");
        String name = scan.nextLine();
        System.out.println("Enter customer gender: ");
        String gender = scan.nextLine();
        System.out.println("Enter customer phone: ");
        String phone = scan.nextLine();
        Customer customer = new Customer(name, gender, phone);
        customers.add(customer);

        // Add room
        System.out.println("Booking room");
        printRooms();

        System.out.println("Enter room number: ");
        Room choosenRoom = rooms.stream().filter(r -> r.getId() == scan.nextInt()).findFirst().orElse(null);
        if (choosenRoom == null) {
            System.out.println("Room not found");
            return;
        }

        if (choosenRoom.isAvailable()) {
            System.out.println("Room is already booked");
            return;
        }

        choosenRoom.setAvailable(false);
        customer.addRoom(choosenRoom);
        System.out.println("Room booked successfully");

    }

    public void addRoom(Room room) {
        rooms.add(room);

    }

    public void addRoom() {
        Scanner scan = new Scanner(System.in);
        // Add room
        System.out.println("Add room:");
        System.out.println("Choose room type: 1. Standard 2. Deluxe 3. Suite");
        int choice = scan.nextInt();
        scan.nextLine();

        System.out.println("Enter room name: ");
        String name = scan.nextLine();
        System.out.println("Enter room price: ");
        double price = scan.nextDouble();
        System.out.println("Enter number of beds: ");
        int beds = scan.nextInt();
        scan.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Is room having shower? 1. Yes 2. No ");
                int shower = scan.nextInt();
                scan.nextLine();
                StandardRoom newSR = new StandardRoom(name, price, beds, shower == 1);
                hotel.addRoom(newSR);
                break;

            case 2:
                System.out.println("Enter furniture:");
                String furniture = scan.nextLine();
                DeluxeRoom newDR = new DeluxeRoom(name, price, beds, furniture);
                hotel.addRoom(newDR);
                break;

            case 3:
                System.out.println("Enter electric devices:");
                String devices = scan.nextLine();
                SuiteRoom newSR = new SuiteRoom(name, price, beds, devices);
                hotel.addRoom(newSR);
                break;

            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    public void removeRoom() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter room id to remove: ");
        int id = scan.nextInt();
        Room room = rooms.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
        if (room == null) {
            System.out.println("Room not found");
            return;
        }
        rooms.remove(room);
        System.out.println("Room removed successfully");

    }

    public void removeRoom(Room room) {
        rooms.remove(room);
    }

    public void checkOut() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter customer id: ");
        int id = scan.nextInt();
        Customer customer = customers.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        if (customer == null) {
            System.out.println("Customer not found");
            return;
        }
        customer.getRooms().stream().forEach(r -> r.setAvailable(true));
        customers.remove(customer);
        System.out.println("Room checked out successfully");
    }

    public void checkOut(Customer customer) {
        customer.getRooms().stream().forEach(r -> r.setAvailable(true));
        customers.remove(customer);
    }

    public List<Room> getAvailableRoom() {
        return rooms.stream().filter(r -> r.isAvailable()).toList();
    }

    public void addService(Service service) {
        services.add(service);
    }

    public void printAllBill() {
        customers.stream().forEach(c -> System.out.println(c.getBill()));
        double total = customers.stream().mapToDouble(c -> c.getBill()).sum();
        System.out.println("Total bill: " + total);
    }

    public void caculate() {
        double salary = employees.stream().mapToDouble(e -> e.getSalary()).sum();
        double bills = customers.stream().mapToDouble(c -> c.getBill()).sum();
        double profit = bills - salary;
        System.out.println("Profit: " + profit);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hotel management system");
        Hotel hotel = new Hotel();

    }
}
