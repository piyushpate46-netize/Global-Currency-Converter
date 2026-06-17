/*
 * =====================================================================
 *  Project   : Global Currency Converter (OOP Project)
 *  Title     : Design and Implementation of a Global Currency Converter
 *              using Static Members in Object-Oriented Programming
 *
 *  NOTE: This is a single-file version with the public class named
 *        "Main" so it runs directly on online compilers
 *        (Programiz, OneCompiler, JDoodle, Replit, etc.) without any
 *        extra setup. Logic is identical to the full project version.
 *        The Main class is placed FIRST in the file because Java's
 *        single-file source launcher runs whichever class appears
 *        first when using "java Main.java" directly.
 *
 *  Concept Demonstrated:
 *      - Static variables (exchange rate constants)
 *      - Static methods (conversion logic)
 *      - Utility class design pattern (no object instantiation required)
 *      - Encapsulation of business logic away from main execution
 * =====================================================================
 */

/**
 * Main
 * -----------------
 * Driver class. Handles user interaction only.
 * Notice that NO object of ConverterEngine is ever created here -
 * the class name itself is used to call the static method,
 * exactly as required by the project specification.
 */
public class Main {

    public static void main(String[] args) {

        java.util.Scanner sc = new java.util.Scanner(System.in);
        char choice;

        System.out.println("=====================================");
        System.out.println("   GLOBAL CURRENCY CONVERTER (to INR)");
        System.out.println("=====================================");

        do {
            ConverterEngine.showSupportedCurrencies();

            double amount;
            String currencyType;

            // ---- Input handling for amount ----
            System.out.print("\nEnter Amount: ");
            while (!sc.hasNextDouble()) {
                System.out.print("Invalid input. Please enter a numeric amount: ");
                sc.next(); // discard invalid token
            }
            amount = sc.nextDouble();

            // ---- Input handling for currency type ----
            System.out.print("Enter Currency (USD/EUR/GBP/JPY/AUD): ");
            currencyType = sc.next();

            // ---- Calling static method directly via class name ----
            double result = ConverterEngine.convert(amount, currencyType);

            if (result == -1) {
                System.out.println("\nError: Invalid currency type or amount entered.");
                System.out.println("Please choose from: USD, EUR, GBP, JPY, AUD");
            } else {
                System.out.printf("\nConverted Amount in INR: Rs. %.2f%n", result);
            }

            // ---- Ask user to continue or exit ----
            // If no more input is available (e.g. compiler gave only one set
            // of values), stop the loop gracefully instead of crashing.
            System.out.print("\nDo you want to convert another amount? (y/n): ");
            if (!sc.hasNext()) {
                break;
            }
            choice = sc.next().charAt(0);
            System.out.println();

        } while (choice == 'y' || choice == 'Y');

        System.out.println("Thank you for using Global Currency Converter!");
        sc.close();
    }
}

/**
 * ConverterEngine
 * -----------------
 * This is a UTILITY class. It is never instantiated.
 * All exchange rates and conversion logic live here as static members,
 * so they can be accessed directly via the class name:
 *
 *      ConverterEngine.convert(100, "USD");
 *
 * This centralizes exchange-rate management and avoids creating
 * unnecessary objects just to perform a simple calculation.
 */
class ConverterEngine {

    // ---------------------------------------------------------------
    // Static final exchange rate constants (Base currency -> INR)
    // 'static'  -> belongs to the class, not to any object
    // 'final'   -> value cannot be changed once assigned (constant)
    // ---------------------------------------------------------------
    public static final double USD_TO_INR = 83.0;
    public static final double EUR_TO_INR = 90.0;
    public static final double GBP_TO_INR = 105.0;
    public static final double JPY_TO_INR = 0.56;
    public static final double AUD_TO_INR = 54.0;

    // ---------------------------------------------------------------
    // Private constructor (optional best practice)
    // Prevents any accidental object creation from outside the class,
    // reinforcing that ConverterEngine is meant to be used statically.
    // ---------------------------------------------------------------
    private ConverterEngine() {
        // Utility class - instantiation not required and intentionally blocked.
    }

    /**
     * Static method to convert a given amount from a supported
     * foreign currency into Indian Rupees (INR).
     *
     * @param amount        amount to be converted
     * @param currencyType  currency code (USD, EUR, GBP, JPY, AUD)
     * @return converted amount in INR, or -1 if currency is invalid
     */
    public static double convert(double amount, String currencyType) {

        // Defensive check for negative or null/empty input
        if (amount < 0 || currencyType == null || currencyType.trim().isEmpty()) {
            return -1;
        }

        // Normalize input (so "usd", "Usd", " USD " etc. are all accepted)
        String currency = currencyType.trim().toUpperCase();

        // Conditional logic to select the correct static rate
        switch (currency) {
            case "USD":
                return amount * USD_TO_INR;
            case "EUR":
                return amount * EUR_TO_INR;
            case "GBP":
                return amount * GBP_TO_INR;
            case "JPY":
                return amount * JPY_TO_INR;
            case "AUD":
                return amount * AUD_TO_INR;
            default:
                // Invalid / unsupported currency type
                return -1;
        }
    }

    /**
     * Utility method to display all supported currencies and their
     * current static exchange rates. Purely informational for the user.
     */
    public static void showSupportedCurrencies() {
        System.out.println("\nSupported Currencies (Rate to INR):");
        System.out.println("-------------------------------------");
        System.out.printf("%-6s : Rs. %.2f per unit%n", "USD", USD_TO_INR);
        System.out.printf("%-6s : Rs. %.2f per unit%n", "EUR", EUR_TO_INR);
        System.out.printf("%-6s : Rs. %.2f per unit%n", "GBP", GBP_TO_INR);
        System.out.printf("%-6s : Rs. %.2f per unit%n", "JPY", JPY_TO_INR);
        System.out.printf("%-6s : Rs. %.2f per unit%n", "AUD", AUD_TO_INR);
        System.out.println("-------------------------------------");
    }
}
