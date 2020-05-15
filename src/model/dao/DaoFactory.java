package model.dao;

import model.impl.SellerDaoJBDC;

public class DaoFactory {

    public static SellerDao createSellerDao() {
        return new SellerDaoJBDC();
    }
}
