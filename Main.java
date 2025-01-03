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
                    students.add(StudentActions.createStudent());

                    // Save the updated list to the CSV file
                    StudentActions.saveToCSV(students, filename);
                    break;
                case 2:
                    // Read and display all students
                    StudentActions.readStudents(students);

                    break;
                case 3:
                    // Update an existing student's details
                    StudentActions.updateStudent(students, sc);

                    // Save the updated list to the CSV file
                    StudentActions.saveToCSV(students, filename);
                    break;
                case 4:
                    // Delete a student from the list
                    StudentActions.deleteStudent(students, sc);

                    // Save the updated list to the CSV file
                    StudentActions.saveToCSV(students, filename);
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

