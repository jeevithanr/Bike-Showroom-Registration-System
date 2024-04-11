import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;

public class BikeShowroomDaoImpl implements BikeShowroomDao {

    // Method to register a showroom
   // @Override
    public int registerShowroom(int showroomID, String brandName, String showroomName, String ownerName,
                                 String location, String contactNo, String email, String website,
                                 boolean hasServiceCenter, String createdBy, Timestamp createdTime,
                                 String modifiedBy, Timestamp modifiedTime) throws SQLException {
        Connection connection = null;
        Statement statement = null;
		int result = 0;
        try {
            // Get database connection
            connection = DBconnection.getConnection();
            statement = connection.createStatement();
            // Construct insert query
            String insertQuery = "INSERT INTO showroom (showroom_id, brand_name, showroom_name, owner_name, location, " +
                    "contact_no, email, website, has_service_center, created_by, created_time, modified_by, modified_time) " +
                    "VALUES (" + showroomID + ", '" + brandName + "', '" + showroomName + "', '" + ownerName + "', '" +
                    location + "', '" + contactNo + "', '" + email + "', '" + website + "', " + hasServiceCenter + ", '" +
                    createdBy + "', '" + createdTime + "', '" + modifiedBy + "', '" + modifiedTime + "')";	
            // Execute insert query
            result = statement.executeUpdate(insertQuery);
        } catch (SQLException e) {
            // Throw SQLException with appropriate error message
            throw new SQLException(e);
        } finally {
            // Close resources in finally block
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		return result;
    }


    // Method to display all showrooms
    @Override
    public List<HashMap<String, Object>> displayAllShowrooms() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<HashMap<String, Object>> showrooms = new ArrayList<>();

        try {
            // Get database connection
            connection = DBconnection.getConnection();
            statement = connection.createStatement();
            // Construct select query
            String query = "SELECT * FROM showroom";
            resultSet = statement.executeQuery(query);

            // Iterate through result set and populate list with showroom details
            while (resultSet.next()) {
                HashMap<String, Object> showroom = new HashMap<>();
                showroom.put("showroom_id", resultSet.getInt("showroom_id"));
                showroom.put("brand_name", resultSet.getString("brand_name"));
                showroom.put("showroom_name", resultSet.getString("showroom_name"));
                showroom.put("owner_name", resultSet.getString("owner_name"));
                showroom.put("location", resultSet.getString("location"));
                showroom.put("contact_no", resultSet.getString("contact_no"));
                showroom.put("email", resultSet.getString("email"));
                showroom.put("website", resultSet.getString("website"));
                showroom.put("has_service_center", resultSet.getBoolean("has_service_center"));
                showroom.put("created_by", resultSet.getString("created_by"));
                showroom.put("created_time", resultSet.getTimestamp("created_time"));
                showroom.put("modified_by", resultSet.getString("modified_by"));
                showroom.put("modified_time", resultSet.getTimestamp("modified_time"));
                showrooms.add(showroom);
            }
            
        } catch (SQLException e) {
            // Throw SQLException with appropriate error message
            throw new SQLException("Error displaying all showrooms", e);
        } finally {
            // Close resources in finally block
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return showrooms;
    }

    // Method to display showroom(s) based on selected field
    @Override
    public List<HashMap<String, Object>> displayShowroom(int selectedField, Object fieldValue) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<HashMap<String, Object>> showrooms = new ArrayList<>();

        try {
            // Get database connection
            connection = DBconnection.getConnection();
            statement = connection.createStatement();

            // Construct SQL query based on selected field
            String query = "";
            switch (selectedField) {
                case 1: // Showroom ID
                    query = "SELECT * FROM showroom WHERE showroom_id = " + fieldValue;
                    break;
                case 2: // Showroom Name
                    query = "SELECT * FROM showroom WHERE showroom_name = '" + fieldValue + "'";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid selected field");
            }
            resultSet = statement.executeQuery(query);

            // Populate list with showroom details
            while (resultSet.next()) {
                HashMap<String, Object> showroom = new HashMap<>();
                showroom.put("showroom_id", resultSet.getInt("showroom_id"));
                showroom.put("brand_name", resultSet.getString("brand_name"));
                showroom.put("showroom_name", resultSet.getString("showroom_name"));
                showroom.put("owner_name", resultSet.getString("owner_name"));
                showroom.put("location", resultSet.getString("location"));
                showroom.put("contact_no", resultSet.getString("contact_no"));
                showroom.put("email", resultSet.getString("email"));
                showroom.put("website", resultSet.getString("website"));
                showroom.put("has_service_center", resultSet.getBoolean("has_service_center"));
                showroom.put("created_by", resultSet.getString("created_by"));
                showroom.put("created_time", resultSet.getTimestamp("created_time"));
                showroom.put("modified_by", resultSet.getString("modified_by"));
                showroom.put("modified_time", resultSet.getTimestamp("modified_time"));
                showrooms.add(showroom);
            }

        } catch (SQLException e) {
            // Throw SQLException with appropriate error message
            throw new SQLException( e);
        } finally {
            // Close resources in finally block
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return showrooms;
    }

    // Method to update a showroom
    @Override
    public void updateShowroom(HashMap<String, Object> updatedValues, String showroomName) throws SQLException {
		Connection connection = null;
		Statement statement = null;

		try {
			// Get database connection
			connection = DBconnection.getConnection();
			statement = connection.createStatement();

			// Construct update query
			String query = "UPDATE showroom SET ";
			boolean first = true;
			for (Map.Entry<String, Object> entry : updatedValues.entrySet()) {
				if (!first) {
					query += ", ";
				}
				query += entry.getKey() + " = ";
				Object value = entry.getValue();
				if (value instanceof String) {
					query += "'" + value + "'";
				} else {
					query += value;
				}
				first = false;
			}
			query += " WHERE showroom_name = '" + showroomName + "'";

			// Execute update query
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// Throw SQLException with appropriate error message
			throw new SQLException( e.getMessage());
		} finally {
			// Close resources in finally block
			try {
				if (statement != null) statement.close();
				if (connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
    // Method to delete a showroom
    @Override
    public void deleteShowroom(int showroomID) throws SQLException {
        Connection connection = null;
        Statement statement = null;

        try {
            // Get database connection
            connection = DBconnection.getConnection();
            statement = connection.createStatement();

            // Construct delete query
            String query = "DELETE FROM showroom WHERE showroom_id = " + showroomID;

            // Execute delete query
            statement.executeUpdate(query);
        } catch (SQLException e) {
            // Throw SQLException with appropriate error message
            throw new SQLException( e.getMessage());
        } finally {
            // Close resources in finally block
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
