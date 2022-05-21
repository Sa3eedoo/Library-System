package sample;

import java.io.File;
import java.util.Formatter;
import java.util.Scanner;

public class IssuedBook {

    private String id;
    private String bookCallNumber;
    private String studentId;
    private String studentName;
    private String studentContact;
    private String issuedDate;

    public static String[] ids = new String[100];
    public static String[] bookCallNumbers = new String[100];
    public static String[] studentIds = new String[100];
    public static String[] studentNames = new String[100];
    public static String[] studentContacts = new String[100];
    public static String[] issuedDates = new String[100];

    public static int numberOfIssuedBooks;

    private static Scanner read;
    private static Formatter write;

    public IssuedBook(String id, String bookCallNumber, String studentId, String studentName,
                      String studentContact, String issuedDate) {

        this.id = id;
        this.bookCallNumber = bookCallNumber;
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentContact = studentContact;
        this.issuedDate = issuedDate;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookCallNumber() {
        return bookCallNumber;
    }

    public void setBookCallNumber(String bookCallNumber) {
        this.bookCallNumber = bookCallNumber;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentContact() {
        return studentContact;
    }

    public void setStudentContact(String studentContact) {
        this.studentContact = studentContact;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public static void writeIssuedBooksFile() {

        //Open file
        try {
            write = new Formatter("IssuedBooks.txt");
        }catch (Exception e) {
            AlertBox.display("Error","Could not find Books file");
        }

        //Write file

        for (int i = 0;i < numberOfIssuedBooks;i++) {
            write.format("%s %s %s %s %s %s\n", ids[i], bookCallNumbers[i], studentIds[i],
                    studentNames[i], studentContacts[i], issuedDates[i]);
        }

        //Close File
        write.close();

    }

    public static void readIssuedBooksFile() {

        //Open File
        try {
            read = new Scanner(new File("IssuedBooks.txt"));
        }catch (Exception e) {
            AlertBox.display("Error","Could not find Books file");
        }

        //Read file
        numberOfIssuedBooks = 0;
        while (read.hasNext()) {
            ids[numberOfIssuedBooks] = read.next();
            bookCallNumbers[numberOfIssuedBooks] = read.next();
            studentIds[numberOfIssuedBooks] = read.next();
            studentNames[numberOfIssuedBooks] = read.next();
            studentContacts[numberOfIssuedBooks] = read.next();
            issuedDates[numberOfIssuedBooks] = read.next();

            numberOfIssuedBooks++;
        }

        //Close file
        read.close();

    }

}
