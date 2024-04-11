import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    // Main method
    public static void main(String[] args) {
        // Create a Scanner object to take user input
        Scanner input = new Scanner(System.in);
        // Create an instance of BikeShowroomService interface
        BikeShowroomService showroomService = new BikeShowroomServiceImpl();

        try {
            // Hardcoded username
			System.out.println("Enter username:");
            String username = input.nextLine();
            int option;
            // Menu loop
            while(true) {
                // Display menu options
                System.out.println("====================Bike Showroom Registration System========================");
                System.out.println("1. Register Showroom");
                System.out.println("2. Display Showrooms");
                System.out.println("3. Update Showroom");
                System.out.println("4. Delete Showroom");
                System.out.println("5. Exit");
                System.out.print("Select an option: ");
                option = input.nextInt();

                // Switch statement to handle user's choice
                switch (option) {
                    case 1:
                        // Register a showroom
                        registerShowroom(input, showroomService, username);
                        break;
                    case 2:
                        // Display showrooms
                        displayShowrooms(showroomService, input);
                        break;
                    case 3:
                        // Update a showroom
						input.nextLine();
                        updateShowroom(input, showroomService, username);
                        break;
                    case 4:
                        // Delete a showroom
                        deleteShowroom(input, showroomService);
                        break;
                    case 5:
                        // Exit the program
                        System.out.println("Exiting...");
						return;
                    default:
                        // Invalid option
                        System.out.println("Invalid option! Please choose again.");
                        break;
                }
			}
        } catch (Exception e) {
            // Catch any exceptions and print error message
            System.out.println("Error: " + e.getMessage());
        } finally {
            // Close the Scanner object
            input.close();
        }
    }

    // Method to register a showroom
    private static void registerShowroom(Scanner input, BikeShowroomService showroomService, String username) {
        try {
            HashMap<String, Object> showroomDetails = new HashMap<>();
            // Consume newline character
            input.nextLine();
            // Prompt user for showroom details
            System.out.println("Enter Brand name:");
            String brandName = input.nextLine();
            showroomDetails.put("BrandName", brandName);
            System.out.println("Enter Showroom name:");
            String showroomName = input.nextLine();
            showroomDetails.put("ShowroomName", showroomName);
            System.out.println("Enter Owner name:");
            String ownerName = input.nextLine();
            showroomDetails.put("OwnerName", ownerName);
            System.out.println("Enter Location:");
            String location = input.nextLine();
            showroomDetails.put("Location", location);
            System.out.println("Enter Contact number:");
            String contactNo = input.nextLine();
            showroomDetails.put("ContactNo", contactNo);
            System.out.println("Enter Email:");
            String email = input.nextLine();
            showroomDetails.put("Email", email);
            System.out.println("Enter Website (optional, press Enter if not applicable):");
            String website = input.nextLine();
            showroomDetails.put("Website", website.isEmpty() ? null : website);
            System.out.println("Does it have a Service Center? (true/false):");
            boolean hasServiceCenter = input.nextBoolean();
			showroomDetails.put("hasService",hasServiceCenter);
            // Get current timestamp
            Timestamp createdTime = new Timestamp(System.currentTimeMillis());
			showroomDetails.put("Date&Time",createdTime);
            // Call service method to register showroom
			String createdBy = username;
			showroomDetails.put("Createdby",createdBy);
            showroomService.registerShowroom(showroomDetails);
        } catch (Exception e) {
            // Catch any exceptions during showroom registration
            System.out.println("Error registering showroom: " + e.getMessage());
        }
    }



    // Method to display showrooms
    private static void displayShowrooms(BikeShowroomService showroomService, Scanner input) {
        try {
            // Display options to display all showrooms or a specific one
            System.out.println("1. Display all Showrooms");
            System.out.println("2. Display specific Showroom");
            System.out.print("Select an option: ");
            int selectedOption = input.nextInt();

            switch (selectedOption) {
                case 1:
                    // Display all showrooms
                    showroomService.displayAllShowrooms();
                    break;
                case 2:
                    // Display specific showroom based on ID or Name
                    System.out.println("1. Showroom ID");
                    System.out.println("2. Showroom Name");
                    System.out.print("Select an option: ");
                    int selectedField = input.nextInt();
                    Object fieldValue = null;
                    if (selectedField == 1) {
                        // Get ID from user
                        System.out.println("Enter the Showroom ID:");
                        fieldValue = input.nextInt();
                    } else if (selectedField == 2) {
                        // Get Name from user
                        System.out.println("Enter the Showroom Name:");
                        input.nextLine(); // Consume newline character
                        fieldValue = input.nextLine();
                    } else {
                        System.out.println("Invalid option!");
                        return;
                    }
                    // Display selected showroom
                    showroomService.displayShowroom(selectedField, fieldValue);
                    break;
                default:
                    // Invalid option
                    System.out.println("Invalid option! Please choose again.");
                    break;
            }
        } catch (Exception e) {
            // Catch any exceptions during showroom display
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to update a showroom
    private static void updateShowroom(Scanner input, BikeShowroomService showroomService, String username) {
        try {
            // Get showroom name from user
            System.out.println("Enter the Showroom Name:");
            String showroomName = input.nextLine();
            // HashMap to store updated values
            HashMap<String, Object> updatedValues = new HashMap<>();
			updatedValues.put("modified_by",username);
            while (true) {
                // Prompt user to select detail to update
                System.out.println("Select the detail you want to update:");
                System.out.println("1. Brand Name \n2. Owner Name \n3. Location \n4. Contact Number \n5. Email \n6. Website");
                int selectedOption = input.nextInt();
                // Prompt user for new value
                System.out.println("Enter the new value:");
                input.nextLine();
                String value = input.nextLine();
                // Update HashMap based on user's choice
                switch (selectedOption) {
                    case 1:
                        updatedValues.put("brand_name", value);
                        break;
                    case 2:
                        updatedValues.put("owner_name", value);
                        break;
                    case 3:
                        updatedValues.put("location", value);
                        break;
                    case 4:
                        updatedValues.put("contact_no", value);
                        break;
                    case 5:
                        updatedValues.put("email", value);
                        break;
                    case 6:
                        updatedValues.put("website", value);
                        break;
                    default:
                        // Invalid option
                        System.out.println("Invalid option!");
                        break;
                }
                // Ask user if they want to update more details
                System.out.println("Do you like to update more details?");
                char continueOption = input.next().charAt(0);
                if (continueOption != 'y' && continueOption != 'Y') {
                    break;
                }
            }
            // Call service method to update showroom
            showroomService.updateShowroom(updatedValues, showroomName);
        } catch (Exception e) {
            // Catch any exceptions during showroom update
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to delete a showroom
    private static void deleteShowroom(Scanner input, BikeShowroomService showroomService) {
        try {
            // Prompt user for showroom ID
            System.out.println("Enter the Showroom ID:");
            int showroomID = input.nextInt();
            // Call service method to delete showroom
            showroomService.deleteShowroom(showroomID);
        } catch (Exception e) {
            // Catch any exceptions during showroom deletion
            System.out.println("Error: " + e.getMessage());
        }
    }
}
