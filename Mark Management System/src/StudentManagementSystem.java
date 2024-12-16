import java.util.Scanner;

public class StudentManagementSystem {
    static int studentCount = 0;
    static final int MAX_STUDENTS = 100;
    static String[] studentIDs = new String[MAX_STUDENTS];
    static String[] studentNames = new String[MAX_STUDENTS];
    static int[] programmingMarks = new int[MAX_STUDENTS];
    static int[] dbmsMarks = new int[MAX_STUDENTS];
    static boolean[] marksAdded = new boolean[MAX_STUDENTS];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\n===== Student Management System =====");
                System.out.println("1. Add New Student");
                System.out.println("2. Add New Student with Marks");
                System.out.println("3. Add Marks");
                System.out.println("4. Update Student Details");
                System.out.println("5. Update Marks");
                System.out.println("6. Delete Student");
                System.out.println("7. Print Student Details");
                System.out.println("8. Print Student Ranks");
                System.out.println("9. Best in Programming Fundamentals");
                System.out.println("10. Best in Database Management System");
                System.out.println("0. Exit");
                System.out.print("Enter your option: ");


                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number between 0 and 10.");
                    scanner.nextLine();
                    continue;
                }

                int choice = scanner.nextInt();
                scanner.nextLine();

                // Handle menu choices
                switch (choice) {
                    case 1:
                        addNewStudent(scanner);
                        break;
                    case 2:
                        addNewStudentWithMarks(scanner);
                        break;
                    case 3:
                        addMarks(scanner);
                        break;
                    case 4:
                        updateStudentDetails(scanner);
                        break;
                    case 5:
                        updateMarks(scanner);
                        break;
                    case 6:
                        deleteStudent(scanner);
                        break;
                    case 7:
                        printStudentDetails(scanner);
                        break;
                    case 8:
                        printStudentRanks();
                        break;
                    case 9:
                        bestInProgramming();
                        break;
                    case 10:
                        bestInDBMS();
                        break;
                    case 0:
                        System.out.println("Exiting program...");
                        return;
                    default:
                        System.out.println("Invalid option. Please select a number between 0 and 10.");
                }
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    //add new student
    static void addNewStudent(Scanner scanner) {
        if (studentCount >= MAX_STUDENTS) {
            System.out.println("Maximum student limit reached.");
            return;
        }
        while (true) {
            System.out.print("Enter Student ID: ");
            String id = scanner.nextLine();

            if (findStudentIndexByID(id) != -1) {
                System.out.println("Student ID already exists. Try again.");
            } else {
                System.out.print("Enter Student Name: ");
                String name = scanner.nextLine();
                studentIDs[studentCount] = id;
                studentNames[studentCount] = name;
                studentCount++;
                System.out.println("Student added successfully.");

                System.out.print("Do you want to add another student? (y/n): ");
                char back = scanner.next().charAt(0);
                scanner.nextLine();
                if (back != 'y') {
                    break;
                }
            }
        }
    }


    static void addNewStudentWithMarks(Scanner scanner) {
        addNewStudent(scanner);
        if (studentCount > 0) {
            addMarks(scanner);
        }
    }


    //add marks
    static void addMarks(Scanner scanner) {
        while (true) {
            System.out.print("Enter Student ID: ");
            String id = scanner.nextLine();
            int index = findStudentIndexByID(id);
            if (index == -1) {
                System.out.println("Student ID not found.");
            } else if (marksAdded[index]) {
                System.out.println("Marks have already been added for this student.");
            } else {
                programmingMarks[index] = getValidMarks(scanner, "Programming Fundamentals");
                dbmsMarks[index] = getValidMarks(scanner, "Database Management Systems");
                marksAdded[index] = true;
                System.out.println("Marks added successfully.");
            }

            System.out.print("Do you want to add marks for another student? (y/n): ");
            char back = scanner.next().charAt(0);
            scanner.nextLine();
            if (back != 'y') {
                break;
            }
        }
    }


    //update student details
    static void updateStudentDetails(Scanner scanner) {
        while (true) {
            System.out.print("Enter Student ID: ");
            String id = scanner.nextLine();
            int index = findStudentIndexByID(id);
            if (index == -1) {
                System.out.println("Student ID not found.");
            } else {
                System.out.println("Current Name: " + studentNames[index]);
                System.out.print("Enter New Name: ");
                studentNames[index] = scanner.nextLine();
                System.out.println("Student details updated successfully.");
            }

            System.out.print("Do you want to update another student’s details? (y/n): ");
            char back = scanner.next().charAt(0);
            scanner.nextLine();
            if (back != 'y') {
                break;
            }
        }
    }


    //update marks
    static void updateMarks(Scanner scanner) {
        while (true) {
            System.out.print("Enter Student ID: ");
            String id = scanner.nextLine();
            int index = findStudentIndexByID(id);
            if (index == -1) {
                System.out.println("Student ID not found.");
            } else if (!marksAdded[index]) {
                System.out.println("Marks have not been added for this student.");
            } else {
                programmingMarks[index] = getValidMarks(scanner, "Programming Fundamentals");
                dbmsMarks[index] = getValidMarks(scanner, "Database Management Systems");
                System.out.println("Marks updated successfully.");
            }

            System.out.print("Do you want to update marks for another student? (y/n): ");
            char back = scanner.next().charAt(0);
            scanner.nextLine();
            if (back != 'y') {
                break;
            }
        }
    }


    //delete student
    static void deleteStudent(Scanner scanner) {
        while (true) {
            System.out.print("Enter Student ID: ");
            String id = scanner.nextLine();
            int index = findStudentIndexByID(id);
            if (index == -1) {
                System.out.println("Student ID not found.");
            } else {
                for (int i = index; i < studentCount - 1; i++) {
                    studentIDs[i] = studentIDs[i + 1];
                    studentNames[i] = studentNames[i + 1];
                    programmingMarks[i] = programmingMarks[i + 1];
                    dbmsMarks[i] = dbmsMarks[i + 1];
                    marksAdded[i] = marksAdded[i + 1];
                }
                studentCount--;
                System.out.println("Student deleted successfully.");
            }

            System.out.print("Do you want to delete another student? (y/n): ");
            char back = scanner.next().charAt(0);
            scanner.nextLine();
            if (back != 'y') {
                break;
            }
        }
    }


    //print student details
    static void printStudentDetails(Scanner scanner) {
        while (true) {
            System.out.print("Enter Student ID: ");
            String id = scanner.nextLine();
            int index = findStudentIndexByID(id);
            if (index == -1) {
                System.out.println("Student ID not found.");
            } else {
                System.out.println("Student ID: " + studentIDs[index]);
                System.out.println("Name: " + studentNames[index]);
                if (marksAdded[index]) {
                    int totalMarks = programmingMarks[index] + dbmsMarks[index];
                    double averageMarks = totalMarks / 2.0;
                    int rank = calculateRank(index);

                    System.out.println("Programming Marks: " + programmingMarks[index]);
                    System.out.println("DBMS Marks: " + dbmsMarks[index]);
                    System.out.println("Total Marks: " + totalMarks);
                    System.out.println("Average Marks: " + averageMarks);
                    System.out.println("Rank: " + rank);
                } else {
                    System.out.println("Marks not added.");
                }
            }

            System.out.print("Do you want to view details of another student? (y/n): ");
            char back = scanner.next().charAt(0);
            scanner.nextLine();
            if (back != 'y') {
                break;
            }
        }
    }



    //print student rank
    static void printStudentRanks() {
        if (studentCount == 0) {
            System.out.println("No students available.");
            return;
        }


        int[][] studentData = new int[studentCount][2];
        for (int i = 0; i < studentCount; i++) {
            studentData[i][0] = programmingMarks[i] + dbmsMarks[i];
            studentData[i][1] = i;
        }


        java.util.Arrays.sort(studentData, (a, b) -> Integer.compare(b[0], a[0]));

        System.out.println("\nRanks:");
        int rank = 1;
        for (int i = 0; i < studentCount; i++) {
            int totalMarks = studentData[i][0];
            int originalIndex = studentData[i][1];
            System.out.println(rank + ". " + studentNames[originalIndex] + " (Total: " + totalMarks + ")");


            if (i < studentCount - 1 && studentData[i][0] != studentData[i + 1][0]) {
                rank++;
            }
        }
    }


    //subject1 marks
    static void bestInProgramming() {

        if (studentCount == 0) {
            System.out.println("No students available.");
            return;
        }
        int highestMarks = -1;
        int bestStudentIndex = -1;

        for (int i = 0; i < studentCount; i++) {
            if (marksAdded[i] && programmingMarks[i] > highestMarks) {
                highestMarks = programmingMarks[i];
                bestStudentIndex = i;
            }
        }

        if (bestStudentIndex != -1) {
            System.out.println("Best in Programming Fundamentals:");
            System.out.println("Student ID: " + studentIDs[bestStudentIndex]);
            System.out.println("Name: " + studentNames[bestStudentIndex]);
            System.out.println("Marks: " + programmingMarks[bestStudentIndex]);
        } else {
            System.out.println("No marks have been added for any student in Programming Fundamentals.");
        }

    }


    //subject2 marks
    static void bestInDBMS() {

        if (studentCount == 0) {
            System.out.println("No students available.");
            return;
        }
        int highestMarks = -1;
        int bestStudentIndex = -1;

        for (int i = 0; i < studentCount; i++) {
            if (marksAdded[i] && dbmsMarks[i] > highestMarks) {
                highestMarks = dbmsMarks[i];
                bestStudentIndex = i;
            }
        }

        if (bestStudentIndex != -1) {
            System.out.println("Best in Database Management Systems:");
            System.out.println("Student ID: " + studentIDs[bestStudentIndex]);
            System.out.println("Name: " + studentNames[bestStudentIndex]);
            System.out.println("Marks: " + dbmsMarks[bestStudentIndex]);
        } else {
            System.out.println("No marks have been added for any student in Database Management Systems.");
        }
    }

    static int findStudentIndexByID(String id) {
        for (int i = 0; i < studentCount; i++) {
            if (studentIDs[i].equals(id)) {
                return i;
            }
        }
        return -1;
    }

    static int getValidMarks(Scanner scanner, String subject) {
        while (true) {
            System.out.print("Enter " + subject + " Marks (0-100): ");
            int marks = scanner.nextInt();
            if (marks >= 0 && marks <= 100) {
                return marks;
            }
            System.out.println("Invalid marks. Try again.");
        }
    }

    static int calculateRank(int studentIndex) {
        int totalMarks = programmingMarks[studentIndex] + dbmsMarks[studentIndex];
        int rank = 1;

        for (int i = 0; i < studentCount; i++) {
            if (i != studentIndex && marksAdded[i]) {
                int otherTotal = programmingMarks[i] + dbmsMarks[i];
                if (otherTotal > totalMarks) {
                    rank++;
                }
            }
        }

        return rank;
    }


}
