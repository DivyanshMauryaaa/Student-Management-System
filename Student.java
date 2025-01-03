public class Student {
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
