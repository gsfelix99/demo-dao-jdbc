package model.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Departmet;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJBDC implements SellerDao {

    private Connection connection;

    public SellerDaoJBDC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Seller obj) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO seller\n" +
                        "(Name, Email, BirthDate, BaseSalary, DepartmentId)\n" +
                        "VALUES\n" +
                        "(?, ?, ?, ?, ?)",
                        statement.RETURN_GENERATED_KEYS);

            statement.setString(1, obj.getName());
            statement.setString(2, obj.getEmail());
            statement.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            statement.setDouble(4, obj.getBaseSalary());
            statement.setInt(5, obj.getDepartmet().getId());

            int rowAffected = statement.executeUpdate();
            if (rowAffected > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();
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
    public void update(Seller obj) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                        "UPDATE seller\n" +
                            "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ?\n" +
                            "WHERE Id = ?");

            statement.setString(1, obj.getName());
            statement.setString(2, obj.getEmail());
            statement.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            statement.setDouble(4, obj.getBaseSalary());
            statement.setInt(5, obj.getDepartmet().getId());
            statement.setInt(6, obj.getId());

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
    public Seller findById(Integer id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(
                    "SELECT seller.*,department.Name as DepName " +
                    "FROM seller " +
                    "INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "WHERE seller.Id = ?");

            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {

                Departmet departmet = instantiateDepartment(resultSet);

                Seller seller = instantiateSeller(resultSet, departmet);

                return seller;

            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }
        return null;
    }

    private Seller instantiateSeller(ResultSet resultSet, Departmet departmet) throws SQLException {
        Seller obj = new Seller();

        obj.setId(resultSet.getInt("Id"));
        obj.setName(resultSet.getString("Name"));
        obj.setEmail(resultSet.getString("Name"));
        obj.setBaseSalary(resultSet.getDouble("BaseSalary"));
        obj.setBirthDate(resultSet.getDate("BirthDate"));
        obj.setDepartmet(departmet);
        return obj;
    }

    private Departmet instantiateDepartment(ResultSet resultSet) throws SQLException {
        Departmet dep = new Departmet();
        dep.setId(resultSet.getInt("DepartmentId"));
        dep.setName(resultSet.getString("DepName"));

        return dep;
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(
                    "SELECT seller.*,department.Name as DepName " +
                            "FROM seller " +
                            "INNER JOIN department " +
                            "ON seller.DepartmentId = department.Id " +
                            "ORDER BY Name");

            resultSet = statement.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Departmet> map = new HashMap<>();

            while (resultSet.next()) {

                Departmet dep= map.get(resultSet.getInt("DepartmentId"));

                if (dep == null) {
                    dep = instantiateDepartment(resultSet);
                    map.put(resultSet.getInt("DepartmentId"), dep);
                }

                Seller seller = instantiateSeller(resultSet, dep);
                list.add(seller);


            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }

    }

    @Override
    public List<Seller> findByDepartment(Departmet departmet) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(
                    "SELECT seller.*,department.Name as DepName " +
                            "FROM seller " +
                            "INNER JOIN department " +
                            "ON seller.DepartmentId = department.Id " +
                            "WHERE DepartmentId = ? " +
                            "ORDER BY Name");

            statement.setInt(1, departmet.getId());
            resultSet = statement.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Departmet> map = new HashMap<>();

            while (resultSet.next()) {

                Departmet dep= map.get(resultSet.getInt("DepartmentId"));

                if (dep == null) {
                    dep = instantiateDepartment(resultSet);
                    map.put(resultSet.getInt("DepartmentId"), dep);
                }

                Seller seller = instantiateSeller(resultSet, dep);
                list.add(seller);


            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }
    }
}
