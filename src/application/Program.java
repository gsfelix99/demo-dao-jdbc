package application;

import db.DB;
import db.DbIntegrityException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {

        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn;
        PreparedStatement statement = null;

        try {
            conn = DB.getConnection();

            statement = conn.prepareStatement(
                    "DELETE FROM department " +
                            "WHERE " +
                            "Id = ?");

            statement.setInt(1, 5);

            int rowsAffected = statement.executeUpdate();
            System.out.println("Done! Rowns affected: " + rowsAffected);


        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        }
        finally {
            DB.closeStatement(statement);
            DB.closeConnection();
        }
    }
}
