package org.example.Service;

import org.example.Controler.CategorieController;
import org.example.Controler.HistoryController;
import org.example.Controler.TaskController;
import org.example.Controler.UserController;

import java.sql.SQLException;
import java.util.Scanner;



public class ServiceAdmin {

    Scanner scanner = new Scanner(System.in);
    public static final String PURPLE = "\033[0;35m";
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";
    public static final String GREEN = "\033[0;32m";
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";
    public static final String RED = "\033[0;31m";


    TaskController taskController = new TaskController();
    HistoryController historyController = new HistoryController();
    UserController userController =new UserController();
    CategorieController categorieController = new CategorieController();

    public void gestionaDesTaches() throws SQLException
    {
        System.out.print("\n");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  1 ");
        System.out.println(PURPLE + "if you want add task");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  2 ");
        System.out.println(PURPLE + "if you want affect task to under user");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  3 ");
        System.out.println(PURPLE + "if you want show task by date");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  4 ");
        System.out.println(PURPLE + "if you want update task");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  5 ");
        System.out.println(PURPLE + "if you want delete dask");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  6 ");
        System.out.println(PURPLE + "if you want show history of the tasks");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  7 ");
        System.out.println(PURPLE + "if you want show task by priority");
        System.out.print(RED_BOLD_BRIGHT + "ENTER  8 ");
        System.out.println(RED + "if you want back a menu principal");
        int n = scanner.nextInt();
        switch (n)
        {
            case 1: {
                taskController.add();
                System.out.print("\n");
                System.out.println(PURPLE + "You want add new task y/n");
                String b = scanner.next();
                while (b.equals("y")) {

                    taskController.add();
                    System.out.println("\n");
                    System.out.println(PURPLE + "You want add new task y/n");
                    b = scanner.next();
                }
                gestionaDesTaches();
                break;
            }

            case 2: {
                taskController.affecterTache();
                System.out.print("\n");
                System.out.println(PURPLE + "You want affect new task to user y/n");
                String b = scanner.next();
                while (b.equals("y")) {
                    taskController.affecterTache();
                    System.out.print("\n");
                    System.out.println(PURPLE + "You want affect new task to user y/n");
                    b = scanner.next();
                }
                gestionaDesTaches();
                break ;
            }
            case 3: {
                taskController.trieTaskWithDate();
                System.out.print("\n");
                System.out.println(PURPLE + "You want back to menu precedent y/n");
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
             taskController.update();
                System.out.print("\n");
                System.out.println(PURPLE + "You want update new task y/n");
                String b = scanner.next();
                while (b.equals("y"))
                {
                    taskController.update();
                    System.out.print("\n");
                    System.out.println(PURPLE + "You want update new task y/n");
                    b = scanner.next();
                }
                gestionaDesTaches();
                break ;
            }
            case 5: {
                taskController.delete();
                System.out.print("\n");
                System.out.println(PURPLE + "You want delete new task y/n");
                String b = scanner.next();
                while (b.equals("y")) {
                    taskController.delete();
                    System.out.print("\n");
                    System.out.println(PURPLE + "You want delete new task y/n");
                    b = scanner.next();
                }
                gestionaDesTaches();
                break ;
            }
            case 6: {
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
            case 7: {
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
            case 8: {
                choixAdmin();
                break ;
            }
            default:{
                throw new IllegalArgumentException("Unexpected value: " + n);
            }
        }
    }

    public void gestionDesUtilisateurs() throws SQLException
    {
        System.out.print("\n");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER   1 ");
        System.out.println(PURPLE + "if you want add user");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER   2 ");
        System.out.println(PURPLE + "if you want update user");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER   3 ");
        System.out.println(PURPLE + "if you want delete user");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER   4 ");
        System.out.println(PURPLE + "if you want show all users");
        System.out.print(RED_BOLD_BRIGHT + "ENTER   5 ");
        System.out.println(RED + "if you want back to menu principal");
        int n = scanner.nextInt();
        switch (n) {
            case 1: {
                userController.add();
                System.out.print("\n");
                System.out.println(PURPLE + "You want add new user y/n");
                String b = scanner.next();
                while (b.equals("y")) {
                    userController.add();
                    System.out.print("\n");
                    System.out.println(PURPLE + "You want add new user y/n");
                    b = scanner.next();
                }
                gestionDesUtilisateurs();
                break;
            }

            case 2: {
                userController.update();
                System.out.print("\n");
                System.out.println(PURPLE + "You want update new user y/n");
                String b = scanner.next();
                while (b.equals("y")) {
                    userController.update();
                    System.out.print("\n");
                    System.out.println(PURPLE + "You want update new user y/n");
                    b = scanner.next();
                }
                gestionDesUtilisateurs();
                break ;
            }
            case 3: {
                userController.delete();
                System.out.print("\n");
                System.out.println(PURPLE + "You want delete new user y/n");
                String b = scanner.next();
                while (b.equals("y")) {
                    userController.delete();
                    System.out.print("\n");
                    System.out.println(PURPLE + "You want delete new user y/n");
                    b = scanner.next();
                }
                gestionDesUtilisateurs();
                break ;
            }
            case 4: {
                userController.getAll();
                System.out.print("\n");
                System.out.println(RED + "You want back to menu precedent y/n");
                String b = scanner.next();
                if (b.equals("y")){
                    gestionDesUtilisateurs();
                }
                else {
                    break;
                }
                break;
            }
            case 5: {
                choixAdmin();
                break ;
            }
            default:{
                throw new IllegalArgumentException("Unexpected value: " + n);
            }
        }
    }

    public void gestionDesCategories() throws SQLException {
        System.out.print("\n");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER   1 ");
        System.out.println(PURPLE + "if you want add category");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER   2 ");
        System.out.println(PURPLE + "if you want update category");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER   3 ");
        System.out.println(PURPLE + "if you want delete category");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER   4 ");
        System.out.println(PURPLE + "if you want show tasks by category");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER   5 ");
        System.out.println(PURPLE + "if you want show all categories");
        System.out.print(RED_BOLD_BRIGHT + "ENTER   6 ");
        System.out.println(RED + "if you want back to menu principal");
            int n = scanner.nextInt();
            switch (n) {
                case 1: {
                    categorieController.add();
                    System.out.print("\n");
                    System.out.println(PURPLE + "You want add new category y/n");
                    String b = scanner.next();
                    while (b.equals("y")) {
                        categorieController.add();
                        System.out.print("\n");
                        System.out.println(PURPLE + "You want add new category y/n");
                        b = scanner.next();
                    }
                   gestionDesCategories();
                    break;
                }

                case 2: {
                    categorieController.update();
                    System.out.print("\n");
                    System.out.println(PURPLE + "You want update new category y/n");
                    String b = scanner.next();
                    while (b.equals("y")) {
                        categorieController.update();
                        System.out.print("\n");
                        System.out.println(PURPLE + "You want update new category y/n");
                        b = scanner.next();
                    }
                    gestionDesCategories();
                    break ;
                }
                case 3: {
                    categorieController.delete();
                    System.out.print("\n");
                    System.out.println(PURPLE + "You want delete new category y/n");
                    String b = scanner.next();
                    while (b.equals("y")) {
                        categorieController.delete();
                        System.out.print("\n");
                        System.out.println(PURPLE + "You want delete new category y/n");
                        b = scanner.next();
                    }
                    gestionDesCategories();
                    break ;
                }
                case 4: {
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
                case 5: {
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
                case 6: {
                    choixAdmin();
                    break ;
                }

                default:{
                    throw new IllegalArgumentException("Unexpected value: " + n);
                }
            }
    }

    public void choixAdmin() throws SQLException
    {
        System.out.print("\n");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  1 ");
        System.out.println(PURPLE + "if you want management tasks");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  2 ");
        System.out.println(PURPLE + "if you want management users");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  3 ");
        System.out.println(PURPLE + "if you want management categories");
        System.out.print(RED_BOLD_BRIGHT + "ENTER  4 ");
        System.out.println(RED + "if you want stop the program");
        int n = scanner.nextInt();
        switch (n) {
            case 1: {
                gestionaDesTaches();
                break;
            }
            case 2: {
                gestionDesUtilisateurs();
                break ;
            }
            case 3: {
                gestionDesCategories();
                break ;
            }
            case 4: {
                System.out.print("\n");
                System.out.print(GREEN + "SUCCESS -> ");
                System.out.println(RED_BOLD_BRIGHT + "SESSION END");
                break ;
            }
            default: {
                throw new IllegalArgumentException("Unexpected value: " + n);
            }
        }
    }
}
