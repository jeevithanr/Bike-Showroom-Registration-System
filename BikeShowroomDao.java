import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

interface BikeShowroomDao {
    // Method to register a showroom
    int registerShowroom(int showroomID, String brandName, String showroomName, String ownerName, String location,
                          String contactNo, String email, String website, boolean hasServiceCenter, String createdBy,
                          Timestamp createdTime, String modifiedBy, Timestamp modifiedTime) throws Exception;

    // Method to display all showrooms
    List<HashMap<String, Object>> displayAllShowrooms() throws Exception;

    // Method to display showroom(s) based on selected field
    List<HashMap<String, Object>> displayShowroom(int selectedField, Object fieldValue) throws Exception;

    // Method to update a showroom
    void updateShowroom(HashMap<String, Object> updatedValues, String showroomName) throws Exception;

    // Method to delete a showroom
    void deleteShowroom(int showroomID) throws Exception;
}


