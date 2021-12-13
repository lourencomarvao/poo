package special.Loaders;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;

import com.opencsv.CSVReader;

import special.Models.Date;
import special.Models.Lecture;
import special.Models.Times;

public class LectureLoader {

    public static final LinkedList<Lecture> readLecturePath(final String filepath){

        LinkedList<Lecture> lectures = new LinkedList<>();

        try {
           
            final Reader reader = Files.newBufferedReader(Paths.get(filepath));
            final CSVReader csvReader = new CSVReader(reader);
            
            //skips headers
            csvReader.readNext();

            String[] tokens;
            
            while ((tokens = csvReader.readNext()) != null) {
                //System.out.println(tokens[4]);
                lectures.add(creationlectures(tokens));
            }

            csvReader.close();
        } catch (Exception  e) {
            e.printStackTrace();
        }
        return lectures;
    }

    public static final LinkedList<Lecture> readLectureFile(final File file){

        LinkedList<Lecture> lectures = new LinkedList<>();

        try {
           
            final Reader reader = new FileReader(file);
            final CSVReader csvReader = new CSVReader(reader);
            
            //skips headers
            csvReader.readNext();

            String[] tokens;
            
            while ((tokens = csvReader.readNext()) != null) {
                //System.out.println(tokens[4]);
                lectures.add(creationlectures(tokens));
            }

            csvReader.close();
        } catch (Exception  e) {
            e.printStackTrace();
        }
        return lectures;
    }


    private static Lecture creationlectures(String[] tokens){

        // all variables in String form
        final String courseS = tokens[0];
        final String name = tokens[1];
        final String shift = tokens[2];
        final String class_name = tokens[3];
        final String n_studentsS = tokens[4];
        final String Free_SpotsS = tokens[5];
        final String Capacity_OverflowS = tokens[6];
        final String week_day = tokens[7];
        final String startS = tokens[8];
        final String endS = tokens[9];
        final String dateS = tokens[10];
        final String required_room_characteristics = tokens[11];
        //final String classroom = tokens[12];
        final String capacityS = tokens[13];
        final String real_characteristics = tokens[14];

        // Converting to their correct form
        final LinkedList<String> course = new LinkedList<>(Arrays.asList(courseS.split(", ")));
        final int n_students = n_studentsS.isEmpty() ? -1 : Integer.parseInt(n_studentsS);

        boolean Free_Spots = true;
        if(Free_SpotsS.equals("FALSO")){
            Free_Spots = false;
        }

        boolean Capacity_Overflow = true;
        if(Capacity_OverflowS.equals("FALSO")){
           Capacity_Overflow = false;
        }
        final Times start = Times.stringToClassTime(startS);
        final Times end = Times.stringToClassTime(endS);
        final Date date = new Date(dateS);

        final int capacity = capacityS.isEmpty() ? -1 : Integer.parseInt(capacityS);

        Lecture lecture = new Lecture(course, name, shift, class_name, n_students, Free_Spots, Capacity_Overflow, week_day, start, end, date, required_room_characteristics, 
        capacity, real_characteristics);
        return lecture;
    } 
}
