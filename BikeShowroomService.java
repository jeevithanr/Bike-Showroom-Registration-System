import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

interface BikeShowroomService {
    // Method to register a showroom
    void registerShowroom(HashMap<String, Object> showroomDetails);

    // Method to display all showrooms
    List<HashMap<String, Object>> displayAllShowrooms();

    // Method to display showroom(s) based on selected field
    List<HashMap<String, Object>> displayShowroom(int selectedField, Object fieldValue);

    // Method to update a showroom
    void updateShowroom(HashMap<String, Object> updatedValues, String showroomName);

    // Method to delete a showroom
    void deleteShowroom(int showroomID);
}

