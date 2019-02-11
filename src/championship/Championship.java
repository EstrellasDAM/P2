/*
 * A possible solution...
 */
package championship;

import controller.Manager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import model.Sport;
import model.Student;

/**
 *
 * @author mfontana
 */
public class Championship {

    private static Manager manager;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            manager = new Manager();
            manager.init();
            int option = -333;
            do {
                showMenu();
                option = InputAsker.askInt("Choose an option");
                switch (option) {
                    case 1:
                        registerStudent();
                        break;
                    case 2:
                        showStudentsByGrade();
                        break;
                    case 3:
                        showSportsNotPossible();
                        break;
                    case 4:
                        unsubscribeStudent();
                        break;
                    case 5:
                        showStudentsBySport();
                        break;
                    case 6:
                        cancelSport();
                        break;
                    case 0:
                        System.out.println("See you soon...");
                        break;
                    default:
                        System.out.println("Wrong option!");
                }
            } while (option != 0);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void cancelSport() throws IOException {
        if (manager.isEmptySports()) {
            System.out.println("All sports have been canceled.");
        } else {
            String nameSport = askSport();
            Sport sport = manager.getSportByName(nameSport);
            System.out.println("Sport selected: " + sport.getName());
            if (sport.getStudents().isEmpty()) {
                System.out.println("This sport doesn't have students.");
            } else {
                System.out.println("Students enrolled in " + sport.getName());
                for (Student st : sport.getStudents()) {
                    System.out.println(st);
                }
            }
            String answer = InputAsker.askString("Are you sure you want delete this sport (y/n)?", "y", "n");
            if (answer.equals("y")) {
                manager.removeSport(sport);
                System.out.println("The sport has been canceled.");
            } else {
                System.out.println("Canceled process");
            }
        }
    }

    private static void unsubscribeStudent() throws IOException {
        if (manager.isEmptyStudents()) {
            System.out.println("There are no students.");
        } else {
            Student st = askStudent();
            System.out.println("Student selected: " + st);
            String answer = InputAsker.askString("Are you sure you want to unsubscribe (y/n)?", "y", "n");
            if (answer.equalsIgnoreCase("y")) {
                manager.removeStudent(st);
                System.out.println("Student has been removed.");
            } else {
                System.out.println("Canceled process");
            }
        }
    }

    private static void showSportsNotPossible() {
        ArrayList<Sport> sports = manager.getSportsNotPossible();
        if (sports.isEmpty()) {
            System.out.println("All sports can be played!");
        } else {
            System.out.println("Sports can not be played");
            for (Sport s : sports) {
                System.out.println(s.getName() + " - have " + s.getStudents().size() + " students.");
            }
        }
    }

    private static void showStudentsByGrade() {
        String grade = askGrade();
        ArrayList<Student> students = manager.getStudentsByGrade(grade);
        if (students.isEmpty()) {
            System.out.println("There isn't any student of this grade.");
        } else {
            System.out.println("Students of " + grade);
            for (Student s : students) {
                System.out.println(s.getName() + " " + s.getSurname());
            }
        }
    }

    private static void registerStudent() throws IOException {
        if (manager.isEmptySports()) {
            System.out.println("All sports have been canceled. You can't register anyone.");
        } else {
            String name = InputAsker.askString("Name:");
            String surname = InputAsker.askString("Surname:");
            if (!manager.existsStudent(name, surname)) {
                String grade = askGrade();
                String gender = InputAsker.askString("Gender m(male) / f (female): ", "m", "f");
                int age = InputAsker.askInt("Age:", 15, 90);
                String sport = askSport();
                Student st = new Student(name, surname, grade, gender, age);
                manager.registerStudent(st, sport);
                System.out.println("The student has been registered in the sport correctly.");
            } else {
                System.out.println("The student is already enrolled in a sport");
            }
        }
    }

    private static Student askStudent() {
        ArrayList<Student> students = showStudentsBySport();
        int answer = InputAsker.askInt("Write a number of student:", 1, students.size());
        return students.get(answer - 1);
    }

    private static ArrayList<Student> showStudentsBySport() {
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<String> namesSports = manager.getNamesSports();
        int i = 1;
        for (String s : namesSports) {
            Sport sport = manager.getSportByName(s);
            if (!sport.getStudents().isEmpty()) {
                System.out.println("**** " + sport.getName() + "****");
                Collections.sort(sport.getStudents());
                for (Student student : sport.getStudents()) {
                    System.out.println(i + " - " + student.getSurname() + ", " + student.getName());
                    i++;
                    students.add(student);
                }
            }
        }
        return students;
    }

    private static String askSport() {
        ArrayList<String> namesSports = manager.getNamesSports();
        String answer = InputAsker.askString("Choose sport:", namesSports);
        answer = manager.normalizeNameSport(answer);
        return answer;
    }

    private static String askGrade() {
        ArrayList<String> grades = manager.getGrades();
        String answer = InputAsker.askString("Choose your grade:", grades);
        return answer;
    }

    private static void showMenu() {
        System.out.println("*** Jornades Esportives 2018 ***");
        System.out.println("1. Student registration");
        System.out.println("2. View students of grade");
        System.out.println("3. View sports can not be played");
        System.out.println("4. Unsubscribe student");
        System.out.println("5. Show students by sport");
        System.out.println("6. Cancel sport");
        System.out.println("0. Exit");
    }

}
