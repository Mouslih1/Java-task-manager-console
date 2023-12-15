package org.example.Controler;

import org.example.DAO.HistoryDAO;
import org.example.Model.History;
import org.example.Model.Task;
import org.example.Model.User;

import java.time.LocalDate;
import java.util.Scanner;

public class HistoryController {
    Scanner scanner = new Scanner(System.in);

    public static void addHistory(User user, Task task, String libelle, LocalDate date_creation)
    {
        History history = new History(user, task,  libelle, date_creation);
        HistoryDAO.addHistory(history);
    }

    public void getAll()
    {
        HistoryDAO.getAll();
    }
}
