import java.util.*;

abstract class Person {
    protected int id;
    protected String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract void displayInfo();
}

class Student extends Person {
    private int age;

    public Student(int id, String name, int age) {
        super(id, name);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public void displayInfo() {
        System.out.println("Student ID: " + id + ", Name: " + name + ", Age: " + age);
    }
}

class StudentManagementSystem {
    private Map<Integer, Student> students;

    public StudentManagementSystem() {
        students = new HashMap<>();
    }

    public void addStudent(int id, String name, int age) {
        if (students.containsKey(id)) {
            System.out.println("Student with ID " + id + " already exists.");
        } else {
            students.put(id, new Student(id, name, age));
            System.out.println("Student added successfully.");
        }
    }

    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            students.values().forEach(Student::displayInfo);
        }
    }

    public void deleteStudent(int id) {
        if (students.remove(id) != null) {
            System.out.println("Student with ID " + id + " deleted successfully.");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }
}

class student {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();

        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Delete Student");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();  // consume the newline
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student age: ");
                    int age = scanner.nextInt();
                    sms.addStudent(id, name, age);
                    break;
                case 2:
                    sms.viewStudents();
                    break;
                case 3:
                    System.out.print("Enter student ID to delete: ");
                    id = scanner.nextInt();
                    sms.deleteStudent(id);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
