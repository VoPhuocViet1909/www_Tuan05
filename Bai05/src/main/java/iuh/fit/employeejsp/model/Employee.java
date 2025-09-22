package iuh.fit.employeejsp.model;

public class Employee {
    private int id;
    private String name;
    private int departmentId;
    private double salary;

    // Constructor rỗng (bắt buộc cho JSP/Servlet)
    public Employee() {
    }

    // Constructor đầy đủ
    public Employee(int id, String name, int departmentId, double salary) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.salary = salary;
    }

    // Constructor không có id (dùng khi thêm mới)
    public Employee(String name, int departmentId, double salary) {
        this.name = name;
        this.departmentId = departmentId;
        this.salary = salary;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", departmentId=" + departmentId +
                ", salary=" + salary +
                '}';
    }
}
