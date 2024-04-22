import java.security.Provider;
import java.util.List;

public class Employee extends Person {
    private boolean status;
//    private List<Service> task;
    private int unitTask;
    private double salary;
    private String job;
    public Employee(int ID, String name, boolean gender, String phone, boolean status, int unitTask, double salary, String job) {
        super(ID, name, gender, phone);
        this.status = status;
        this.unitTask = unitTask;
        this.salary = salary;
        this.job = job;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public int getUnitTask() {
        return unitTask;
    }
    public void setUnitTask(int unitTask) {
        this.unitTask = unitTask;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Employee{" +
                super.toString()+
                "status=" + status +
                ", unitTask=" + unitTask +
                ", salary=" + salary +
                ", job='" + job + '\'' +
                '}';
    }
}
