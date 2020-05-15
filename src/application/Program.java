package application;

import db.DB;
import db.DbIntegrityException;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Departmet;
import model.entities.Seller;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println("----------Test 1----------");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("\n\n----------Test 2----------");
        Departmet departmet  = new Departmet(2, null);
        List<Seller> list = sellerDao.findByDepartment(departmet);
        for (Seller obj: list) {
            System.out.println(obj);
        }

        System.out.println("\n\n----------Test 3----------");

        List<Seller> list1 = sellerDao.findAll();
        for (Seller obj: list1) {
            System.out.println(obj);
        }

        System.out.println("\n\n----------Test 4----------");
        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.00, departmet);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id = " + newSeller.getId());


        System.out.println("\n\n----------Test 5----------");
        seller = sellerDao.findById(1);
        seller.setName("Martha Waine");
        sellerDao.update(seller);
        System.out.println("Update cimpleted");

        System.out.println("\n\n----------Test 6----------");
        System.out.println("Enter id for Delete: ");
        int id = scanner.nextInt();
        sellerDao.deleteById(id);


    }
}
