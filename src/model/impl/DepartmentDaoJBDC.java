package model.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDAO;
import model.entities.Departmet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepartmentDaoJBDC implements DepartmentDAO {
    private Connection connection;

    public DepartmentDaoJBDC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Departmet obj) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO department\n" +
                            "(Id, Name)\n" +
                            "VALUES\n" +
                            "(?, ?)",
                    statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, obj.getId());
            statement.setString(2, obj.getName());

            int rowAffected = statement.executeUpdate();
            if (rowAffected > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();
                System.out.println(resultSet);
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(resultSet);
            } else {
                throw new DbException("Unexpected error! No rows affected");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(statement);
        }

    }

    @Override
    public void update(Departmet obj) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "UPDATE department\n" +
                            "SET Name= ?\n" +
                            "WHERE Id = ?");

            statement.setString(1, obj.getName());
            statement.setInt(2, obj.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(statement);
        }

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Departmet findById(Integer id) {
        return null;
    }

    @Override
    public List<Departmet> findAll() {
        return null;
    }
}
