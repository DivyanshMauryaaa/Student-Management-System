import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Student> students = new ArrayList<>(); // Store students
    public static String filename;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] options = { "Create", "Read", "Update", "Delete", "Exit" };

        // Ask for the filename to save data
        System.out.print("Enter the filename to save student data (e.g., students.csv): ");
        filename = sc.nextLine();

        // Load existing data from CSV if available
        loadFromCSV(filename);

        while (true) {
            // Display options
            System.out.println("\nOptions: ");

            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }

            System.out.print("Enter option: ");
            int option = sc.nextInt();
            sc.nextLine(); // consume newline after nextInt()

            switch (option) {
                case 1:
                    // Create a new student and add to the list
                    students.add(Students.createStudent());

                    // Save the updated list to the CSV file
                    Students.saveToCSV(students, filename);
                    break;
                case 2:
                    // Read and display all students
                    Students.readStudents(students);

                    break;
                case 3:
                    // Update an existing student's details
                    Students.updateStudent(students, sc);

                    // Save the updated list to the CSV file
                    Students.saveToCSV(students, filename);
                    break;
                case 4:
                    // Delete a student from the list
                    Students.deleteStudent(students, sc);

                    // Save the updated list to the CSV file
                    Students.saveToCSV(students, filename);
                    break;
                case 5:
                    // Exit the program
                    System.out.println("Exiting...");

                    return;
                default:
                    // Handle invalid options
                    System.out.println("Invalid option");

            }
        }
    }

    // Load data from CSV file
    public static void loadFromCSV(String filename) {
        File file = new File(filename);

        // Check if the file exists
        if (!file.exists()) {
            System.out.println("No data file found. Starting with an empty list.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            // Skip the header line
            reader.readLine();

            // Process each line in the file
            while ((line = reader.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");

                // If the line does not have exactly 7 columns, it's invalid
                if (data.length != 7) {
                    System.out.println("Skipping invalid line (incorrect number of columns): " + line);
                    continue;
                }

                try {
                    // Trim spaces and parse the student details
                    String name = data[0].trim();
                    String section = data[1].trim();
                    String address = data[2].trim();
                    int age = Integer.parseInt(data[3].trim());
                    int id = Integer.parseInt(data[4].trim());
                    int grade = Integer.parseInt(data[5].trim());
                    long governmentID = Long.parseLong(data[6].trim());

                    // Add the student to the list
                    Main.students.add(new Student(name, section, address, age, id, grade, governmentID));

                } catch (NumberFormatException e) {
                    // Handle invalid number format for age, id, grade, or governmentID
                    System.out.println("Skipping line with invalid data (e.g., non-numeric): " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading data from CSV: " + e.getMessage());
        }
    }

}

class Student {
    String name;
    String section;
    String address;
    int age;
    int id;
    int grade;
    long GovernmentID;

    // Constructor for easy creation of Student objects
    public Student(String name, String section, String address, int age, int id, int grade, long GovernmentID) {
        this.name = name;
        this.section = section;
        this.address = address;
        this.age = age;
        this.id = id;
        this.grade = grade;
        this.GovernmentID = GovernmentID;
    }
}

class Students {
    // Create a new student and return the student object
    public static Student createStudent() {
        // Initialising Scanner
        Scanner sc = new Scanner(System.in);

        // Getting details
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter section: ");
        String section = sc.nextLine();

        System.out.print("Enter address: ");
        String address = sc.nextLine();

        System.out.print("Enter age: ");
        int age = sc.nextInt();

        System.out.print("Enter id: ");
        int id = sc.nextInt();

        System.out.print("Enter grade: ");
        int grade = sc.nextInt();

        System.out.print("Enter Government ID: ");
        long GovernmentID = sc.nextLong(); // Long data type for more digit & value support

        // Return the created student details
        return new Student(name, section, address, age, id, grade, GovernmentID);
    }

    // Display all students in a table with improved formatting
    public static void readStudents(ArrayList<Student> students) {

        // Check if the list is empty
        if (students.isEmpty()) { // If condition applied
            System.out.println("No students to display.");
            return;
        }

        // If the students arrayList is not empty then display the table with details
        // Print header with alignment
        System.out.printf("%-15s %-10s %-25s %-5s %-5s %-5s %-15s%n",
                "Name", "Section", "Address", "Age", "ID", "Grade", "Government ID");

        System.out.println("--------------------------------------------------------------------------------------");

        // Print each student's details
        for (Student student : students) {
            System.out.printf("%-15s %-10s %-25s %-5d %-5d %-5d %-15d%n",
                    student.name, student.section, student.address, student.age,
                    student.id, student.grade, student.GovernmentID);
        }
    }

    // Update an existing student's details
    public static void updateStudent(ArrayList<Student> students, Scanner sc) {
        System.out.print("Enter the ID of the student to update: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline

        Student studentToUpdate = null;
        for (Student student : students) {
            if (student.id == id) {
                studentToUpdate = student;
                break;
            }
        }

        if (studentToUpdate == null) {
            System.out.println("Student with ID " + id + " not found.");
            return;
        }

        System.out.println("Updating student with ID: " + id);
        System.out.println("Enter new details (leave blank to keep current value):");

        System.out.print("Enter name (" + studentToUpdate.name + "): ");
        String name = sc.nextLine();
        if (!name.isEmpty())
            studentToUpdate.name = name;

        System.out.print("Enter section (" + studentToUpdate.section + "): ");
        String section = sc.nextLine();
        if (!section.isEmpty())
            studentToUpdate.section = section;

        System.out.print("Enter address (" + studentToUpdate.address + "): ");
        String address = sc.nextLine();
        if (!address.isEmpty())
            studentToUpdate.address = address;

        System.out.print("Enter age (" + studentToUpdate.age + "): ");
        String ageInput = sc.nextLine();
        if (!ageInput.isEmpty())
            studentToUpdate.age = Integer.parseInt(ageInput);

        System.out.print("Enter grade (" + studentToUpdate.grade + "): ");
        String gradeInput = sc.nextLine();
        if (!gradeInput.isEmpty())
            studentToUpdate.grade = Integer.parseInt(gradeInput);

        System.out.print("Enter Government ID (" + studentToUpdate.GovernmentID + "): ");
        String govIdInput = sc.nextLine();
        if (!govIdInput.isEmpty())
            studentToUpdate.GovernmentID = Long.parseLong(govIdInput);

        System.out.println("Student updated successfully.");
    }

    // Delete a student by their ID
    public static void deleteStudent(ArrayList<Student> students, Scanner sc) {
        System.out.print("Enter the ID of the student to delete: ");
        int id = sc.nextInt();

        Student studentToDelete = null;
        for (Student student : students) {
            if (student.id == id) {
                studentToDelete = student;
                break;
            }
        }

        if (studentToDelete == null) {
            System.out.println("Student with ID " + id + " not found.");
            return;
        }

        students.remove(studentToDelete);
        System.out.println("Student with ID " + id + " has been deleted.");
    }

    // Save the students list to a CSV file
    public static void saveToCSV(ArrayList<Student> students, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Write the header
            writer.write("Name,Section,Address,Age,ID,Grade,Government ID");
            writer.newLine();

            // Write each student's data
            for (Student student : students) {
                writer.write(String.format("%s,%s,%s,%d,%d,%d,%d",
                        student.name, student.section, student.address, student.age,
                        student.id, student.grade, student.GovernmentID));
                writer.newLine();
            }

            System.out.println("Data saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving data to CSV: " + e.getMessage());
        }
    }
}
