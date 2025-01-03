import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentActions {
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
