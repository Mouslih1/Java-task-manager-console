package org.example;


import org.example.Controler.*;
import org.example.DAO.CategorieDAO;
import org.example.DAO.HistoryDAO;
import org.example.DAO.TaskDAO;
import org.example.DAO.UserDAO;
import org.example.Model.Categorie;
import org.example.Model.Priority;
import org.example.Model.Task;
import org.example.Service.ServiceController;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

       {
           // MainController.mainDAO();
           ServiceController.login();
       }

        CsvController.transfererInCsv();
        CsvController.saveInCsv();
    }
}