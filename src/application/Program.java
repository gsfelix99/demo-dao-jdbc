package application;

import db.DB;
import db.DbIntegrityException;
import model.entities.Departmet;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {

        Departmet departmet = new Departmet(1, "Books");
        System.out.println(departmet);
    }
}
