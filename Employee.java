import java.util.ArrayList;
import java.util.List;

// SalariedEntity Interface
interface SalariedEntity {
    float getSalary();
}

// Abstract Employee Class
abstract class Employee implements SalariedEntity {
    private String name;
    protected float salary;

    public Employee(String name, float salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public abstract float getSalary();

    public void raiseSalary(float byPercent) {
        salary += salary * (byPercent / 100.0);
    }
}

// Manager Class
class Manager extends Employee {
    private List<Employee> employees;

    public Manager(String name, float salary) {
        super(name, salary);
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    @Override
    public float getSalary() {
        float totalSalary = salary;
        for (Employee employee : employees) {
            totalSalary += employee.getSalary() * 0.05; // 5% of each subordinate's salary
        }
        return totalSalary;
    }
}

// Subordinate Class
class Subordinate extends Employee {
    public Subordinate(String name, float salary) {
        super(name, salary);
    }

    @Override
    public float getSalary() {
        return salary;
    }
}

// Subcontractor Class
class Subcontractor implements SalariedEntity {
    private long taxNumber;
    private float salary;

    public Subcontractor(long taxNumber, float salary) {
        this.taxNumber = taxNumber;
        this.salary = salary;
    }

    @Override
    public float getSalary() {
        return salary;
    }

    public long getTaxNumber() {
        return taxNumber;
    }
}

// Company Class
class Company {
    private List<SalariedEntity> salariedEntities;

    public Company() {
        this.salariedEntities = new ArrayList<>();
    }

    public void addEntity(SalariedEntity entity) {
        salariedEntities.add(entity);
    }

    public void removeEntity(SalariedEntity entity) {
        salariedEntities.remove(entity);
    }

    public void raiseEmployeeSalaries(float byPercent) {
        for (SalariedEntity entity : salariedEntities) {
            if (entity instanceof Employee) {
                ((Employee) entity).raiseSalary(byPercent);
            }
        }
    }

    public void printSalaries() {
        for (SalariedEntity entity : salariedEntities) {
            if (entity instanceof Employee) {
                System.out.println("Employee: " + ((Employee) entity).getName() + " Salary: " + entity.getSalary());
            } else if (entity instanceof Subcontractor) {
                System.out.println("Subcontractor Tax Number: " + ((Subcontractor) entity).getTaxNumber() + " Salary: " + entity.getSalary());
            }
        }
    }
}

// Main Class
class Main {
    public static void main(String[] args) {
        // Create Subordinates
        Subordinate emp1 = new Subordinate("Alice", 50000);
        Subordinate emp2 = new Subordinate("Bob", 55000);
        Subordinate emp3 = new Subordinate("Charlie", 60000);

        // Create Manager
        Manager manager = new Manager("David", 80000);
        manager.addEmployee(emp1);
        manager.addEmployee(emp2);

        // Create Subcontractor
        Subcontractor subcontractor = new Subcontractor(123456789L, 40000);

        // Create Company
        Company company = new Company();
        company.addEntity(emp1);
        company.addEntity(emp2);
        company.addEntity(emp3);
        company.addEntity(manager);
        company.addEntity(subcontractor);

        // Print Initial Salaries
        System.out.println("Initial Salaries:");
        company.printSalaries();

        // Raise Employee Salaries
        company.raiseEmployeeSalaries(10);

        // Print Updated Salaries
        System.out.println("\nSalaries After 10% Raise:");
        company.printSalaries();
    }
}
