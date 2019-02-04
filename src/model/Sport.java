package model;

import java.util.ArrayList;

/**
 * The Sport class represents a sport. All sports have a name (String) and a
 * a minimum number of players to be able to play (int). It also has a list of 
 * registered students. Initially this list is empty.
 * 
 * @see Student
 * 
 * @author mfontana
 */
public class Sport {

    private String name;
    private int minPlayers;
    private ArrayList<Student> students;

    /** 
     * Construct a new Sport whith name and minPlayers. The list of  
     * registered students is initialized with an empty list.
     * 
     * @param name value of name
     * @param minPlayers minimum number of players to be able to play
     */
    public Sport(String name, int minPlayers) {
        this.name = name;
        this.minPlayers = minPlayers;
        students = new ArrayList<>();
    }

    /**
     * Returns true if the sport can be practiced, that is, if the number 
     * of students enrolled is greater than or equal to the minimum number 
     * of players needed (minPlayers).
     * 
     * @return boolean
     */
    public boolean isPossible() {
        return getStudents().size() >= getMinPlayers();
    }
    
    /**
     * Returns true if the student passed as a parameter is subscribed 
     * to the sport.
     * 
     * @param s Student to find
     * @return boolean
     */
    public boolean existsStudent(Student s) {
        return students.contains(s);
    }

    /**
     * Returns ArrayList of students who are studying the grade.
     * 
     * @param grade the name of grade to find
     * @return ArrayList of Student
     */
    public ArrayList<Student> getStudentsByGrade(String grade) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getGrade().equalsIgnoreCase(grade)) {
                result.add(s);
            }
        }
        return result;
    }
    
    /**
     * Get the value of minPlayers
     *
     * @return the value of minPlayers
     */
    public int getMinPlayers() {
        return minPlayers;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the value of minPlayers
     *
     * @param minPlayers new value of minPlayers
     */
    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    /**
     * Get the value of students
     *
     * @return the value of students
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * Set the value of students
     *
     * @param students new value of students
     */
    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    /**
     * Returns a string representation of the Sport.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "-" + name + "-" + minPlayers;
    }

    
    
}
