package model.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Departmet;
import model.entities.Seller;

import java.sql.*;
import java.util.List;

public class SellerDaoJBDC implements SellerDao {

    private Connection connection;

    public SellerDaoJBDC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

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

                Departmet departmet = new Departmet();
                departmet.setId(resultSet.getInt("DepartmentId"));
                departmet.setName(resultSet.getString("DepName"));

                Seller obj = new Seller();

                obj.setId(resultSet.getInt("Id"));
                obj.setName(resultSet.getString("Name"));                obj.setName(resultSet.getString("Name"));
                obj.setEmail(resultSet.getString("Name"));
                obj.setBaseSalary(resultSet.getDouble("BaseSalary"));
                obj.setBirthDate(resultSet.getDate("BirthDate"));
                obj.setDepartmet(departmet);

                return obj;

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

    @Override
    public List<Seller> findAll() {
        return null;
    }
}
