package model.dao;

import db.DB;
import model.impl.DepartmentDaoJBDC;
import model.impl.SellerDaoJBDC;

public class DaoFactory {

    public static SellerDao createSellerDao() {
        return new SellerDaoJBDC(DB.getConnection());
    }

    public static DepartmentDAO createDepartmentDao() {
        return new DepartmentDaoJBDC(DB.getConnection());
    }
}
