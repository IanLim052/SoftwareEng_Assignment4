package EyeWear;

import java.io.*;
import java.util.*;

public class Prescription {
    private int prescID;
    private String firstName;
    private String lastName;
    private String address;
    private float sphere;
    private float axis;
    private float cylinder;
    private String examinationDate;
    private String optometrist;
    private String[] remarkTypes = {"Client", "Optometrist"};
    private ArrayList<String> postRemarks = new ArrayList<>();

    // Constructor
    public Prescription(String firstName, String lastName, String address, float sphere, float axis, 
                        float cylinder, String examinationDate, String optometrist) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.sphere = sphere;
        this.axis = axis;
        this.cylinder = cylinder;
        this.examinationDate = examinationDate;
        this.optometrist = optometrist;
    }

    // Method to add a prescription
    public boolean addPrescription() {
        if (isValidPrescription()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("presc.txt", true))) {
                writer.write(String.format("First Name: %s,\n Last Name: %s, \n Address: %s, \n Sphere: %.2f, " +
                                "\n Axis: %.2f, \n Cylinder: %.2f, \n Exam Date: %s, \n Optometrist: %s\n",
                        firstName, lastName, address, sphere, axis, cylinder, examinationDate, optometrist));
                writer.flush();
                return true;
            } catch (IOException e) {
                e.printStackTrace();  // This will show the full stack trace in the console
            }
        }
        return false;
    }
    
    // Validation for the prescription details
    private boolean isValidPrescription() {
        return isNameValid(firstName) && isNameValid(lastName) &&
                address.length() >= 20 &&
                sphere >= -20.00f && sphere <= 20.00f &&
                cylinder >= -4.00f && cylinder <= 4.00f &&
                axis >= 0 && axis <= 180 &&
                isOptometristNameValid(optometrist) &&
                isValidDate(examinationDate);
    }

    // Validate name length
    private boolean isNameValid(String name) {
        return name != null && name.length() >= 4 && name.length() <= 15;
    }

    // Validate optometrist name length
    private boolean isOptometristNameValid(String name) {
        return name != null && name.length() >= 8 && name.length() <= 25;
    }

    // Simple date validation (DD/MM/YY format)
    private boolean isValidDate(String date) {
        return date != null && date.matches("\\d{2}/\\d{2}/\\d{2}");
    }

    // Method to add a remark to the prescription
    public boolean addRemark(String remark, String remarkCategory) {
        if (isValidRemark(remark, remarkCategory)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("review.txt", true))) {
                writer.write(String.format("Remark: %s, Category: %s\n", remark, remarkCategory));
                writer.flush();
                return true;
            } catch (IOException e) {
                System.out.println("Error writing to file.");
            }
        }
        return false;
    }

    // Validate remark and category
    private boolean isValidRemark(String remark, String remarkCategory) {
        if ((remarkCategory.equals(remarkTypes[0]) || remarkCategory.equals(remarkTypes[1])) &&
                postRemarks.size() < 2 && isWordCountValid(remark)) {
            return Character.isUpperCase(remark.charAt(0));
        }
        return false;
    }

    // Validate word count in the remark
    private boolean isWordCountValid(String remark) {
        int wordCount = remark.split("\\s+").length;
        return wordCount >= 6 && wordCount <= 20;
    }
}
