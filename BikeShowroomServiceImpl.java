import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BikeShowroomServiceImpl implements BikeShowroomService {
	
    // Create an instance of the BikeShowroomDaoImpl class
    private BikeShowroomDao showroomDAO = new BikeShowroomDaoImpl();

	   // Method to register a showroom
	public void registerShowroom(HashMap<String, Object> showroomDetails) {
		try {
			// Call the registerShowroom method of the showroomDAO instance
			String brandName = String.valueOf(showroomDetails.get("BrandName"));
			String showroomName = String.valueOf(showroomDetails.get("ShowroomName"));
			String ownerName = String.valueOf(showroomDetails.get("OwnerName"));
			String location = String.valueOf(showroomDetails.get("Location"));
			String contactNo = String.valueOf(showroomDetails.get("ContactNo"));
			String email = String.valueOf(showroomDetails.get("Email"));
			String website = String.valueOf(showroomDetails.get("Website"));
			boolean hasServiceCenter = Boolean.valueOf(String.valueOf(showroomDetails.get("hasService")));
			String createdBy = String.valueOf(showroomDetails.get("Createdby"));
			Timestamp createdTime = (Timestamp) showroomDetails.get("Date&Time");
			String modifiedBy = String.valueOf(showroomDetails.get("Createdby"));
			Timestamp modifiedTime = (Timestamp) showroomDetails.get("Date&Time");
			int showroomID=0;
			int result = showroomDAO.registerShowroom(showroomID,brandName, showroomName, ownerName, location, contactNo, email,
			website, hasServiceCenter, createdBy, createdTime, modifiedBy, modifiedTime);
			if(result != 0){
				System.out.println("Showroom registered successfully!");
			}
			else{
				System.out.println("Error to insert showrrom");
			}
		} catch (Exception e) {
			// Print error message if an exception occurs
			System.out.println("Error registering showroom: " + e.getMessage());
		}
	}


    // Method to display all showrooms
    public List<HashMap<String, Object>> displayAllShowrooms() {
        try {
            // Call the displayAllShowrooms method of the showroomDAO instance
            List<HashMap<String, Object>> showrooms = showroomDAO.displayAllShowrooms();
            if (showrooms.isEmpty()) {
                // If no showrooms found, print an error message
                System.out.println("No showrooms found.");
            } else {
                // Print values of each showroom
                for (HashMap<String, Object> showroom : showrooms) {
                    for (String key : showroom.keySet()) {
                        System.out.println(key + " : " + showroom.get(key));
                    }
                    System.out.println(); // Add a newline after each showroom
                }
            }
            return showrooms;
        } catch (Exception e) {
            // Print error message if an exception occurs
            System.out.println("Error displaying all showrooms: " + e.getMessage());
            return null;
        }
    }


	// Method to display showroom(s) based on selected field
	public List<HashMap<String, Object>> displayShowroom(int selectedField, Object fieldValue) {
		try {
			// Check if the provided field value exists in the database
			List<HashMap<String, Object>> showrooms = showroomDAO.displayShowroom(selectedField, fieldValue);
			if (showrooms.isEmpty()) {
				// If no showroom found with the provided field value, print an error message
				System.out.println("No showroom found with the provided value.");
			} else {
				// Print values of each showroom
				for (HashMap<String, Object> showroom : showrooms) {
					for (Map.Entry<String, Object> entry : showroom.entrySet()) {
						System.out.println(entry.getKey() + " : " + entry.getValue());
					}
					System.out.println(); // Add a newline after each showroom
				}
			}
			return showrooms;
        } catch (Exception e) {
            // Print error message if an exception occurs
            System.out.println("Error displaying showroom: " + e.getMessage());
            return null;
        }
    }

	// Method to update a showroom
	public void updateShowroom(HashMap<String, Object> updatedValues, String showroomName) {
		try {
			// Check if the showroom exists with the provided name
			List<HashMap<String, Object>> showrooms = showroomDAO.displayShowroom(2, showroomName);
			if (showrooms.isEmpty()) {
				// If no showroom found with the provided name, print an error message
				System.out.println("No showroom found with the provided name.");
			} else {
				// Get the existing showroom details
				HashMap<String, Object> existingShowroom = showrooms.get(0);

				// Check if the provided values are already the same as existing values
				boolean hasSameValues = true;
				for (String key : updatedValues.keySet()) {
					Object updatedValue = updatedValues.get(key);
					Object existingValue = existingShowroom.get(key);

					// Perform case-insensitive comparison
					if (!updatedValue.toString().equalsIgnoreCase(existingValue.toString())) {
						hasSameValues = false;
						break;
					}
				}

				if (hasSameValues) {
					System.out.println("Same value already exists for the provided update.");
				} else {
					// Call the updateShowroom method of the showroomDAO instance
					showroomDAO.updateShowroom(updatedValues, showroomName);
					System.out.println("Showroom updated successfully!");
				}
			}
		} catch (Exception e) {
			// Print error message if an exception occurs
			System.out.println("Error updating showroom: " + e.getMessage());
		}
	}

	


    // Method to delete a showroom
    public void deleteShowroom(int showroomID) {
        try {
            // Check if the showroom exists with the provided ID
            List<HashMap<String, Object>> showrooms = showroomDAO.displayShowroom(1, showroomID);
            if (showrooms.isEmpty()) {
                // If no showroom found with the provided ID, print an error message
                System.out.println("No showroom found with the provided ID.");
                return;
            }
            // Call the deleteShowroom method of the showroomDAO instance
            showroomDAO.deleteShowroom(showroomID);
            System.out.println("Showroom deleted successfully!");
        } catch (Exception e) {
            // Print error message if an exception occurs
            System.out.println("Error deleting showroom: " + e.getMessage());
        }
    }
}
