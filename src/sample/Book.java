package sample;

import java.io.File;
import java.util.Formatter;
import java.util.Scanner;

public class Book {

    private String bookCallNumber;
    private String name;
    private String author;
    private String publisher;
    private String quantity;

    public static String[] bookCallNumbers = new String[100];
    public static String[] names = new String[100];
    public static String[] authors = new String[100];
    public static String[] publishers = new String[100];
    public static String[] quantities = new String[100];

    public static int numberOfBooks;

    private static Scanner read;
    private static Formatter write;

    public Book(String bookCallNumber, String name, String author, String publisher, String quantity) {
        this.bookCallNumber = bookCallNumber;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.quantity = quantity;
    }

    public String getBookCallNumber() {
        return bookCallNumber;
    }

    public void setBookCallNumber(String bookCallNumber) {
        this.bookCallNumber = bookCallNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public static void writeBooksFile() {

        //Open file
        try {
            write = new Formatter("Books.txt");
        }catch (Exception e) {
            AlertBox.display("Error","Could not find Books file");
        }

        //Write file

        for (int i = 0;i < numberOfBooks;i++) {
            write.format("%s %s %s %s %s\n", bookCallNumbers[i], names[i], authors[i], publishers[i], quantities[i]);
        }

        //Close File
        write.close();

    }

    public static void readBooksFile() {

        //Open File
        try {
            read = new Scanner(new File("Books.txt"));
        }catch (Exception e) {
            AlertBox.display("Error","Could not find Books file");
        }

        //Read file
        numberOfBooks = 0;
        while (read.hasNext()) {
            bookCallNumbers[numberOfBooks] = read.next();
            names[numberOfBooks] = read.next();
            authors[numberOfBooks] = read.next();
            publishers[numberOfBooks] = read.next();
            quantities[numberOfBooks] = read.next();

            numberOfBooks++;
        }

        //Close file
        read.close();

    }

}
