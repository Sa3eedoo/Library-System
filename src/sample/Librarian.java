package sample;

import java.io.File;
import java.util.Formatter;
import java.util.Scanner;

public class Librarian {

    private String id;
    private String name;
    private String password;
    private String email;
    private String address;
    private String city;
    private String contact;

    public static String[] ids = new String[100];
    public static String[] names = new String[100];
    public static String[] passwords = new String[100];
    public static String[] emails = new String[100];
    public static String[] addresses = new String[100];
    public static String[] cities = new String[100];
    public static String[] contacts = new String[100];
    public static int numberOfLibrarians;

    private static Scanner read;
    private static Formatter write;

    public Librarian(String id, String name, String password, String email, String address, String city, String contact) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.address = address;
        this.city = city;
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public static void writeLibrariansFile() {

        //Open file
        try {
            write = new Formatter("Librarians.txt");
        }catch (Exception e) {
            AlertBox.display("Error","Could not find Librarians file");
        }

        //Write file

        for (int i = 0;i < numberOfLibrarians;i++) {
            write.format("%s %s %s %s %s %s %s\n", ids[i], names[i], passwords[i], emails[i], addresses[i],
                    cities[i], contacts[i]);
        }

        //Close File
        write.close();

    }

    public static void readLibrariansFile() {

        //Open File
        try {
            read = new Scanner(new File("Librarians.txt"));
        }catch (Exception e) {
            AlertBox.display("Error","Could not find Librarians file");
        }

        //Read file
        numberOfLibrarians = 0;
        while (read.hasNext()) {
            ids[numberOfLibrarians] = read.next();
            names[numberOfLibrarians] = read.next();
            passwords[numberOfLibrarians] = read.next();
            emails[numberOfLibrarians] = read.next();
            addresses[numberOfLibrarians] = read.next();
            cities[numberOfLibrarians] = read.next();
            contacts[numberOfLibrarians] = read.next();
            numberOfLibrarians++;
        }

        //Close file
        read.close();

    }

}
