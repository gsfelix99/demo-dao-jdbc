package application;

import db.DB;
import db.DbIntegrityException;
import model.entities.Departmet;
import model.entities.Seller;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {
    Date date = new Date(30/04);
        Departmet departmet = new Departmet(1, "Books");
        Seller seller = new Seller(21, "Bob", "bob@gmail.com", date, 3000.00, departmet);
        System.out.println(seller);

    }
}
