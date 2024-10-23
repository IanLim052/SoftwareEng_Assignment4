package EyeWear;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PrescriptionTest {

	

    // Test invalid first name and last name length for addPrescription()
    @Test
    public void testAddPrescription_InvalidNameLength() {
        Prescription presc = new Prescription("Jo", "Do", "1234 Example Street, Suburb, Postcode, Country", 
                                                -5.50f, 90f, -1.75f, "22/10/24", "Dr. Andrew Smith");
        assertFalse(presc.addPrescription());
    }

    // Test invalid address length for addPrescription()
    @Test
    public void testAddPrescription_InvalidAddressLength() {
        Prescription presc = new Prescription("John", "Doe", "Short Address", 
                                                -5.50f, 90f, -1.75f, "22/10/24", "Dr. Andrew Smith");
        assertFalse(presc.addPrescription());
    }

    // Test invalid sphere value for addPrescription()
    @Test
    public void testAddPrescription_InvalidSphereValue() {
        Prescription presc = new Prescription("John", "Doe", "1234 Example Street, Suburb, Postcode, Country", 
                                                -21.00f, 90f, -1.75f, "22/10/24", "Dr. Andrew Smith");
        assertFalse(presc.addPrescription());
    }

    // Test invalid cylinder value for addPrescription()
    @Test
    public void testAddPrescription_InvalidCylinderValue() {
        Prescription presc = new Prescription("John", "Doe", "1234 Example Street, Suburb, Postcode, Country", 
                                                -5.50f, 90f, -5.00f, "22/10/24", "Dr. Andrew Smith");
        assertFalse(presc.addPrescription());
    }

    // Test invalid axis value for addPrescription()
    @Test
    public void testAddPrescription_InvalidAxisValue() {
        Prescription presc = new Prescription("John", "Doe", "1234 Example Street, Suburb, Postcode, Country", 
                                                -5.50f, 190f, -1.75f, "22/10/24", "Dr. Andrew Smith");
        assertFalse(presc.addPrescription());
    }

    // Test invalid examination date format for addPrescription()
    @Test
    public void testAddPrescription_InvalidExaminationDate() {
        Prescription presc = new Prescription("John", "Doe", "1234 Example Street, Suburb, Postcode, Country", 
                                                -5.50f, 90f, -1.75f, "2024-10-22", "Dr. Andrew Smith");
        assertFalse(presc.addPrescription());
    }

    // Test invalid optometrist name length for addPrescription()
    @Test
    public void testAddPrescription_InvalidOptometristNameLength() {
        Prescription presc = new Prescription("John", "Doe", "1234 Example Street, Suburb, Postcode, Country", 
                                                -5.50f, 90f, -1.75f, "22/10/24", "Dr. An");
        assertFalse(presc.addPrescription());
    }

    // Test valid remark for addRemark()
    @Test
    public void testAddRemark_ValidRemark() {
        Prescription presc = new Prescription("John", "Doe", "1234 Example Street, Suburb, Postcode, Country", 
                                                -5.50f, 90f, -1.75f, "22/10/24", "Dr. Andrew Smith");
        assertTrue(presc.addRemark("This prescription is valid for one year.", "Client"));
    }

    // Test invalid word count for remark in addRemark()
    @Test
    public void testAddRemark_InvalidWordCount() {
        Prescription presc = new Prescription("John", "Doe", "1234 Example Street, Suburb, Postcode, Country", 
                                                -5.50f, 90f, -1.75f, "22/10/24", "Dr. Andrew Smith");
        assertFalse(presc.addRemark("Too short.", "Client"));
    }

    // Test invalid remark starting with lowercase letter in addRemark()
    @Test
    public void testAddRemark_InvalidStartingLetter() {
        Prescription presc = new Prescription("John", "Doe", "1234 Example Street, Suburb, Postcode, Country", 
                                                -5.50f, 90f, -1.75f, "22/10/24", "Dr. Andrew Smith");
        assertFalse(presc.addRemark("this remark starts with a lowercase letter.", "Client"));
    }

    // Test invalid remark category in addRemark()
    @Test
    public void testAddRemark_InvalidRemarkCategory() {
        Prescription presc = new Prescription("John", "Doe", "1234 Example Street, Suburb, Postcode, Country", 
                                                -5.50f, 90f, -1.75f, "22/10/24", "Dr. Andrew Smith");
        assertFalse(presc.addRemark("This remark starts with a valid upper case.", "Customer")); // invalid category
    }

    // Test exceeding 2 remarks for a prescription in addRemark()
    @Test
    public void testAddRemark_ExceedRemarksLimit() {
        Prescription presc = new Prescription("John", "Doe", "1234 Example Street, Suburb, Postcode, Country", 
                                                -5.50f, 90f, -1.75f, "22/10/24", "Dr. Andrew Smith");
        presc.addRemark("This is the first valid remark.", "Client");
        presc.addRemark("This is the second valid remark.", "Optometrist");
        assertFalse(presc.addRemark("This is an extra remark.", "Client"));
    }
    
}
