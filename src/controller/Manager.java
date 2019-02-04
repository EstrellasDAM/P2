package controller;

import java.io.IOException;
import java.util.ArrayList;
import model.Sport;
import model.SportEven;
import model.Student;
import persistence.InputOutputFile;

/**
 * Class to manage the championship.
 * 
 * @author mfontana
 */
public class Manager {

    private ArrayList<Sport> sports;

    /**
     * Create a Manager class. Load data from files, if exists, or create 
     * default sports.
     */
    public Manager()  {
        sports = new ArrayList<>();
    }

    /**
     * Load data from files, if exists, or create default sports.
     * @throws IOException 
     */
    public void init() throws IOException {
        if (InputOutputFile.existsDataFolder() && InputOutputFile.existsSporstFile()) {
            sports = InputOutputFile.loadData();
        } else {
            createDefaultSports();
            InputOutputFile.writeSports(sports);
        }
    }
    
    /** 
     * Returns true if there is no student in any sport.
     * 
     * @return boolean
     */
    public boolean isEmptyStudents() {
        for (Sport s : sports) {
            if (!s.getStudents().isEmpty()) {
                return false;
            }
        } 
        return true;
    }
    
    /** 
     * Returns true if there is no sport.
     * 
     * @return boolean
     */
    public boolean isEmptySports() {
        return sports.isEmpty();
    }

    /**
     * Register a Student in the sport with name nameSport. Save it in file.
     * 
     * @param st - Student
     * @param nameSport - String
     * @throws IOException - if there is a problem with the files 
     */
    public void registerStudent(Student st, String nameSport) throws IOException {
        Sport s = getSportByName(nameSport);
        if (s != null) {
            s.getStudents().add(st);
            InputOutputFile.saveStudentToSport(st, nameSport);
        }
    }

    /**
     * Get Sport by name.
     * 
     * @param name
     * @return Sport
     */
    public Sport getSportByName(String name) {
        for (Sport s : sports) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }

    /**
     * Returns true if a student with name and surname already exists.
     * 
     * @param name
     * @param surname
     * @return boolean
     */
    public boolean existsStudent(String name, String surname) {
        Student aux = new Student(name, surname);
        for (Sport s : sports) {
            if (s.existsStudent(aux)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Normalizes the name of the sport.
     * 
     * @param s
     * @return String
     */
    public String normalizeNameSport(String s) {
        switch (s.toLowerCase()) {
            case "futbol":
                s = "Futbol";
                break;
            case "futbolsala":
                s = "FutbolSala";
                break;
            case "basquet":
                s = "Basquet";
                break;
            case "badminton":
                s = "Badminton";
                break;
            case "voleyplaya" :
                s = "VoleyPlaya";
                break;
        }
        return s;
    }

    /**
     * Create defaults sports and add to Array List sports.
     * 
     */
    private void createDefaultSports() {
        sports.add(new Sport("Futbol", 22));
        sports.add(new Sport("FutbolSala", 14));
        sports.add(new Sport("Basquet", 10));
        sports.add(new SportEven("Badminton", 2));
        sports.add(new SportEven("VoleyPlaya", 4));
    }

    /**
     * Remove a sport. Remove data in files too.
     * 
     * @param sport
     * @throws IOException - if there is a problem with the files 
     */
    public void removeSport(Sport sport) throws IOException {
        sports.remove(sport);
        InputOutputFile.writeSports(sports);
        InputOutputFile.removeFileSports(sport);
    }
    
    /**
     * Remove a student. Remove data in files too.
     * 
     * @param student
     * @throws IOException - if there is a problem with the files 
     */
    public void removeStudent(Student student) throws IOException {
        Sport s = getSportByStudent(student);
        if (s != null) {
            s.getStudents().remove(student);
            InputOutputFile.saveStudentsBySport(s);
        } 
    }
    
    /**
     * Get a sport by student.
     * 
     * @param student
     * @return Sport
     */
    public Sport getSportByStudent(Student student) {
        for (Sport s : sports) {
            if (s.existsStudent(student)) {
                return s;
            }
        }
        return null;
    }
    
    /**
     * Returns the list of sports that can't be played.
     * 
     * @return ArrayList Sport
     */
    public ArrayList<Sport> getSportsNotPossible() {
        ArrayList<Sport> sportsNotPossible = new ArrayList<>();
        for (Sport s : sports) {
            if (!s.isPossible()) {
                sportsNotPossible.add(s);
            }
        }
        return sportsNotPossible;
    }

    /**
     * Returns a list of student of a grade.
     * 
     * @param grade
     * @return ArrayList Student
     */
    public ArrayList<Student> getStudentsByGrade(String grade) {
        ArrayList<Student> students = new ArrayList<>();
        for (Sport s : sports) {
            students.addAll(s.getStudentsByGrade(grade));
        }
        return students;
    }

    /**
     * Returns a list of the name of sports from championship.
     * 
     * @return ArrayList String
     */
    public ArrayList<String> getNamesSports() {
        ArrayList<String> namesSports = new ArrayList<>();
        for (Sport s : sports) {
            namesSports.add(s.getName());
        }
        return namesSports;
    }

    /**
     * Returns a list of the names of grades from school.
     * 
     * @return ArrayList String
     */
    public ArrayList<String> getGrades() {
        ArrayList<String> grades = new ArrayList<>();
        grades.add("DAW1M");
        grades.add("DAW2M");
        grades.add("DAM1T");
        grades.add("DAM2T1");
        grades.add("DAM2T2");
        return grades;
    }

}
