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
    
    public boolean addPrescription() {
        if (isValidPrescription()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("presc.txt", true))) {
                writer.write(String.format("First Name: %s, Last Name: %s, Address: %s, Sphere: %.2f, " +
                                "Axis: %.2f, Cylinder: %.2f, Exam Date: %s, Optometrist: %s\n",
                        firstName, lastName, address, sphere, axis, cylinder, examinationDate, optometrist));
                writer.flush();
                return true;
            } catch (IOException e) {
                System.out.println("Error writing to file.");
            }
        }
        return false;
    }

    private boolean isValidPrescription() {
        return isNameValid(firstName) && isNameValid(lastName) &&
                address.length() >= 20 &&
                sphere >= -20.00f && sphere <= 20.00f &&
                cylinder >= -4.00f && cylinder <= 4.00f &&
                axis >= 0 && axis <= 180 &&
                isOptometristNameValid(optometrist) &&
                isValidDate(examinationDate);
    }

    private boolean isNameValid(String name) {
        return name.length() >= 4 && name.length() <= 15;
    }

    private boolean isOptometristNameValid(String name) {
        return name.length() >= 8 && name.length() <= 25;
    }

    private boolean isValidDate(String date) {
        // Simplistic date validation: check if it's in the format DD/MM/YY
        return date.matches("\\d{2}/\\d{2}/\\d{2}");
    }

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

    private boolean isValidRemark(String remark, String remarkCategory) {
        if (remarkCategory.equals(remarkTypes[0]) || remarkCategory.equals(remarkTypes[1])) {
            if (postRemarks.size() < 2 && isWordCountValid(remark)) {
                return Character.isUpperCase(remark.charAt(0));
            }
        }
        return false;
    }

    private boolean isWordCountValid(String remark) {
        int wordCount = remark.split("\\s+").length;
        return wordCount >= 6 && wordCount <= 20;
    }
}
