package model;

import java.util.Objects;

/**
 * The Student class represents a student subscribed to a sport.
 * 
 * @see Sport
 * 
 * @author mfontana
 */
public class Student implements Comparable<Student> {

    private String name;
    private String surname;
    private String grade;
    private String gender;
    private int age;

    /**
     * Construct a new Student with properties. A Student is Comparable to be 
     * able to order.
     * 
     * @param name
     * @param surname
     * @param grade
     * @param gender
     * @param age 
     */    
    public Student(String name, String surname, String grade, String gender, int age) {
        this.name = name;
        this.surname = surname;
        this.grade = grade;
        this.gender = gender;
        this.age = age;
    }

    /**
     * Construct a new Student with name and surname. Necessary to check if 
     * a student exists without needing more data.
     * 
     * @param name
     * @param surname 
     */
    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
    

    /**
     * Get the value of age
     *
     * @return the value of age
     */
    public int getAge() {
        return age;
    }

    /**
     * Set the value of age
     *
     * @param age new value of age
     */
    public void setAge(int age) {
        this.age = age;
    }


    /**
     * Get the value of gender
     *
     * @return the value of gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Set the value of gender
     *
     * @param gender new value of gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }


    /**
     * Get the value of grade
     *
     * @return the value of grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Set the value of grade
     *
     * @param grade new value of grade
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * Get the value of surname
     *
     * @return the value of surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Set the value of surname
     *
     * @param surname new value of surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.surname);
        return hash;
    }
    
    /**
     * Two students are equals if their name and surname are equals 
     * (ignore case).
     * 
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (!name.equalsIgnoreCase(other.getName())) {
            return false;
        }
        return surname.equalsIgnoreCase(other.getSurname());
    }

    
    
    @Override
    public String toString() {
        return name + "-" + surname + "-" + grade + "-" + gender + "-" + age;
    }

    /**
     * Two students are comparable by surname, and in case of a tie, by name
     * (ignore case).
     * 
     * @param o - Student
     * @return int
     */
    @Override
    public int compareTo(Student o) {
        if (o.getSurname().equalsIgnoreCase(surname)) {
            return name.compareToIgnoreCase(o.getName());
        }
        return surname.compareToIgnoreCase(o.getSurname());
    }
    
}
