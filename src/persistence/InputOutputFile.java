package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import model.Sport;
import model.SportEven;
import model.Student;

/**
 * InputOutputFile contains methods related to file persistence.
 *
 * @author mfontana
 */
public class InputOutputFile {

    private static final String SEPARATOR = File.separator;
    private static final File DATA_FOLDER = new File("data");
    private static final File SPORTS_FILE = new File(DATA_FOLDER + SEPARATOR + "deportes.txt");

    /**
     * Return true if data folder exists.
     *
     * @return boolean
     */
    public static boolean existsDataFolder() {
        return DATA_FOLDER.exists();
    }

    /**
     * Return true if sports file exists.
     *
     * @return boolean
     */
    public static boolean existsSporstFile() {
        return SPORTS_FILE.exists();
    }

    /**
     * Returns a ArrayList of Sport with all data from files.
     *
     * @return ArrayList Sport
     * @throws IOException - if there is a problem with the files
     */
    public static ArrayList<Sport> loadData() throws IOException {
        ArrayList<Sport> sports = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(SPORTS_FILE));
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] data = linea.split("-");
            Sport s;
            if (data.length == 3) {
                if (data[0].equals("Sport")) {
                    s = new Sport(data[1], Integer.parseInt(data[2]));
                } else {
                    s = new SportEven(data[1], Integer.parseInt(data[2]));
                }
                sports.add(s);
                readStudentsSport(s);
            }
        }
        return sports;
    }

    /**
     * Load all students enrolled in a sport from his file.
     *
     * @param s - Sport
     * @throws IOException - if there is a problem with the files
     */
    private static void readStudentsSport(Sport s) throws IOException {
        File f = new File(DATA_FOLDER + SEPARATOR + s.getName() + ".txt");
        if (f.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] data = linea.split("-");
                if (data.length == 5) {
                    Student st = new Student(data[0], data[1], data[2], data[3], Integer.parseInt(data[4]));
                    s.getStudents().add(st);
                }
            }
        }
    }

    /**
     * Add a Student to file of the Sport with name nameSport.
     *
     * @param st - Student
     * @param nameSport - String, name of Sport.
     * @throws IOException - if there is a problem with the files
     */
    public static void saveStudentToSport(Student st, String nameSport) throws IOException {
        File f = new File(DATA_FOLDER + SEPARATOR + nameSport + ".txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
        bw.write(st.toString());
        bw.newLine();
        bw.close();
    }

    /**
     * Save all students enrolled in the sport in his file.
     *
     * @param s - Sport
     * @throws IOException - if there is a problem with the files
     */
    public static void saveStudentsBySport(Sport s) throws IOException {
        File f = new File(DATA_FOLDER + SEPARATOR + s.getName() + ".txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        for (Student st : s.getStudents()) {
            bw.write(st.toString());
            bw.newLine();
        }
        bw.close();
    }

    /**
     * Delete the Sport file.
     *
     * @param s -Sport
     */
    public static void removeFileSports(Sport s) {
        File f = new File(DATA_FOLDER + SEPARATOR + s.getName() + ".txt");
        f.delete();
    }

    /**
     * Save sports in sports file.
     *
     * @param sports - ArrayList Sport
     * @throws IOException - if there is a problem with the files
     */
    public static void writeSports(ArrayList<Sport> sports) throws IOException {
        if (!existsDataFolder()) {
            DATA_FOLDER.mkdir();
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(SPORTS_FILE));
        for (Sport s : sports) {
            bw.write(s.toString());
            bw.newLine();
        }
        bw.close();
    }

}
