package Model;

import View.ViewPort;

/**
 *
 * @author Armijos Manfred, Balseca Valeska
 */
public class Validation {

    private static final int MAX_TEU = 24346;         // Maximum TEU capacity for container ships (world record)
    private static final int MAX_PASSENGERS = 10310;  // Maximum passenger capacity for cruise ships (world record)
    private static final Double MAX_LENGTH = 400.00;  // Maximum vessel length in meters (just under 400m)

    private static int capacity = 0;                  // Temporary storage for vessel capacity during validation
    private static boolean capacityOk = false;        // Flag indicating if capacity validation passed
    private static boolean edition = false;           // Flag indicating if the form is in edit mode

    public static int getCapacity() {
        return capacity;
    }

    public static void setCapacity(int capacity) {
        Validation.capacity = capacity;
    }

    public static boolean getCapacityOk() {
        return capacityOk;
    }

    public static void setCapacityOk(boolean capacityOk) {
        Validation.capacityOk = capacityOk;
    }

    public static boolean isEdition() {
        return edition;
    }

    public static void setEdition(boolean edition) {
        Validation.edition = edition;
    }
    
    
    //BASIC VALIDATIONS

    // Validates text: only letters and spaces
    protected static boolean validateText(String text) {
        return text.matches("^[a-zA-Z ]+$");
    }

    // Validates IMO number (7 digits with checksum)
    protected static boolean validateIMO(String imo) {
        boolean success = false;
        if (imo.matches("\\d{7}")) {
            int sum = 0;
            sum += (imo.charAt(0) - '0') * 7;
            sum += (imo.charAt(1) - '0') * 6;
            sum += (imo.charAt(2) - '0') * 5;
            sum += (imo.charAt(3) - '0') * 4;
            sum += (imo.charAt(4) - '0') * 3;
            sum += (imo.charAt(5) - '0') * 2;
            success = (sum % 10) == (imo.charAt(6) - '0');
        }
        return success;
    }

    // Validates vessel length (LOA)
    protected static boolean validateLength(String input) {
        try {
            double length = Double.parseDouble(input);
            return (length >= 100 && length < MAX_LENGTH);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validates TEU capacity
    protected static boolean validateTEU(String input) {
        try {
            int number = Integer.parseInt(input);
            return (number >= 0 && number <= MAX_TEU);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validates passenger capacity
    protected static boolean validateCapacityPassenger(String input) {
        try {
            int number = Integer.parseInt(input);
            return (number >= 0 && number <= MAX_PASSENGERS);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validates quantity against maximum capacity
    protected static boolean validateQuantity(String input, int max) {
        try {
            int number = Integer.parseInt(input);
            return (number >= 0 && number <= max);
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
