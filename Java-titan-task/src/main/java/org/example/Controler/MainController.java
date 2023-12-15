package org.example.Controler;

import org.example.DAO.CategorieDAO;
import org.example.DAO.HistoryDAO;
import org.example.DAO.TaskDAO;
import org.example.DAO.UserDAO;

public class MainController {
    public static void mainDAO()
    {
        UserDAO.getAll();
        CategorieDAO.getAll();
        TaskDAO.getAll();
        HistoryDAO.getAll();
    }
}
