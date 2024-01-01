package org.example.Service;

import org.example.Controler.CategorieController;
import org.example.Controler.HistoryController;
import org.example.Controler.TaskController;
import org.example.Controler.UserController;

import java.sql.SQLException;
import java.util.Scanner;

public class ServiceUser {
    Scanner scanner = new Scanner(System.in);
    TaskController taskController = new TaskController();
    HistoryController historyController = new HistoryController();
    CategorieController categorieController = new CategorieController();
    public static final String PURPLE = "\033[0;35m";
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";
    public static final String GREEN = "\033[0;32m";
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";
    public static final String RED = "\033[0;31m"; // RED


    public void gestionaDesTaches() throws SQLException {
        System.out.print("\n");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  1 ");
        System.out.println(PURPLE + "if you want show task by date");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  2 ");
        System.out.println(PURPLE + "if you want show tasks history");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  3 ");
        System.out.println(PURPLE + "if you want show task by priority");
        System.out.print(RED_BOLD_BRIGHT + "ENTER  4 ");
        System.out.println(RED + "if you want back to menu principal");
        int n = scanner.nextInt();
        switch (n)
        {
            case 1: {
                taskController.trieTaskWithDate();
                System.out.print("\n");
                System.out.println(RED + "You want back to menu precedent y/n");
                String b = scanner.next();
                if (b.equals("y"))
                {
                    gestionaDesTaches();
                }
                else {
                    break;
                }
                break ;
            }
            case 2: {
                historyController.getAll();
                System.out.print("\n");
                System.out.println(RED + "You want back to menu precedent y/n");
                String b = scanner.next();
                if (b.equals("y")){
                    gestionaDesTaches();
                }
                else {
                    break;
                }
                break ;
            }
            case 3: {
                taskController.trieTaskWithPriority();
                System.out.print("\n");
                System.out.println(RED + "You want back to menu precedent y/n");
                String b = scanner.next();
                if (b.equals("y")){
                    gestionaDesTaches();
                }
                else {
                    break;
                }
                break ;
            }
            case 4: {
                choixUser();
                break ;
            }
            default:{
                throw new IllegalArgumentException("Unexpected value: " + n);
            }
        }
    }

    public void gestionDesCategories() throws SQLException
    {
        System.out.print("\n");
        System.out.println(PURPLE_BOLD_BRIGHT + "ENTER  1 ");
        System.out.println(PURPLE + "if you want show tasks by category");
        System.out.println(PURPLE_BOLD_BRIGHT + "ENTER  2 ");
        System.out.println(PURPLE + "if you want show all categories");
        System.out.println(RED_BOLD_BRIGHT + "ENTER  3 ");
        System.out.println(RED + "if you want back to menu principal");
        int n = scanner.nextInt();
        switch (n)
        {
            case 1: {
                taskController.trieTaskWithCategory();
                System.out.print("\n");
                System.out.println(RED + "You want back to menu precedent y/n");
                String b = scanner.next();
                if (b.equals("y")){
                    gestionDesCategories();
                }
                else {
                    break;
                }
                break ;
            }
            case 2: {
                categorieController.getAll();
                System.out.print("\n");
                System.out.println(RED + "You want back to menu precedent y/n");
                String b = scanner.next();
                if (b.equals("y")){
                    gestionDesCategories();
                }
                else {
                    break;
                }
                break;
            }
            case 3: {
                choixUser();
                break ;
            }
            default:{
                throw new IllegalArgumentException("Unexpected value: " + n);
            }
        }
    }

    public void choixUser() throws SQLException
    {
        System.out.print("\n");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  1 ");
        System.out.println(PURPLE + "if you want management tasks");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  2 ");
        System.out.println(RED_BOLD_BRIGHT + "ENTER  3 ");
        System.out.println(RED + "if you want to stop the program");
        int n = scanner.nextInt();
        switch (n)
        {
            case 1: {
                gestionaDesTaches();
                break;
            }
            case 2: {
                gestionDesCategories();
                break ;
            }
            case 3: {
                System.out.print("\n");
                System.out.print(GREEN + "SUCCESS -> ");
                System.out.println(RED_BOLD_BRIGHT + "SESSION END");
                break ;
            }
            default:{
                throw new IllegalArgumentException("Unexpected value: " + n);
            }
        }
    }
}
