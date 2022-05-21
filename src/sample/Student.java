package sample;

import java.io.File;
import java.util.Formatter;
import java.util.Scanner;

public class Student {

    public static String[] ids = new String[100];
    public static String[] names = new String[100];
    public static String[] passwords = new String[100];
    public static String[] emails = new String[100];
    public static String[] addresses = new String[100];
    public static String[] cities = new String[100];
    public static String[] contacts = new String[100];
    public static int numberOfStudents;

    private static Scanner read;
    private static Formatter write;


    public static void writeStudentsFile() {

        //Open file
        try {
            write = new Formatter("Students.txt");
        }catch (Exception e) {
            AlertBox.display("Error","Could not find Students file");
        }

        //Write file

        for (int i = 0; i < numberOfStudents; i++) {
            write.format("%s %s %s %s %s %s %s\n", ids[i], names[i], passwords[i], emails[i], addresses[i],
                    cities[i], contacts[i]);
        }

        //Close File
        write.close();

    }

    public static void readStudentsFile() {

        //Open File
        try {
            read = new Scanner(new File("Students.txt"));
        }catch (Exception e) {
            AlertBox.display("Error","Could not find Students file");
        }

        //Read file
        numberOfStudents = 0;
        while (read.hasNext()) {
            ids[numberOfStudents] = read.next();
            names[numberOfStudents] = read.next();
            passwords[numberOfStudents] = read.next();
            emails[numberOfStudents] = read.next();
            addresses[numberOfStudents] = read.next();
            cities[numberOfStudents] = read.next();
            contacts[numberOfStudents] = read.next();
            numberOfStudents++;
        }

        //Close file
        read.close();

    }

}
