package org.example.Service;

import org.example.Controler.UserController;
import org.example.DAO.CategorieDAO;
import org.example.DAO.HistoryDAO;
import org.example.DAO.TaskDAO;
import org.example.DAO.UserDAO;
import org.example.Model.User;

import java.sql.SQLException;
import java.sql.SQLOutput;

public class ServiceController {
    static UserController userController = new UserController();
    static ServiceUser serviceUser = new ServiceUser();
    static ServiceAdmin serviceAdmin = new ServiceAdmin();
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";


    public  static void login() throws SQLException
    {
        String art = PURPLE_BOLD_BRIGHT + "                                                                                                                    \n" +
                "@@@@@@@   @@@@@@    @@@@@@   @@@  @@@     @@@@@@@@@@    @@@@@@   @@@  @@@   @@@@@@    @@@@@@@@  @@@@@@@@  @@@@@@@   \n" +
                "@@@@@@@  @@@@@@@@  @@@@@@@   @@@  @@@     @@@@@@@@@@@  @@@@@@@@  @@@@ @@@  @@@@@@@@  @@@@@@@@@  @@@@@@@@  @@@@@@@@  \n" +
                "  @@!    @@!  @@@  !@@       @@!  !@@     @@! @@! @@!  @@!  @@@  @@!@!@@@  @@!  @@@  !@@        @@!       @@!  @@@  \n" +
                "  !@!    !@!  @!@  !@!       !@!  @!!     !@! !@! !@!  !@!  @!@  !@!!@!@!  !@!  @!@  !@!        !@!       !@!  @!@  \n" +
                "  @!!    @!@!@!@!  !!@@!!    @!@@!@!      @!! !!@ @!@  @!@!@!@!  @!@ !!@!  @!@!@!@!  !@! @!@!@  @!!!:!    @!@!!@!   \n" +
                "  !!!    !!!@!!!!   !!@!!!   !!@!!!       !@!   ! !@!  !!!@!!!!  !@!  !!!  !!!@!!!!  !!! !!@!!  !!!!!:    !!@!@!    \n" +
                "  !!:    !!:  !!!       !:!  !!: :!!      !!:     !!:  !!:  !!!  !!:  !!!  !!:  !!!  :!!   !!:  !!:       !!: :!!   \n" +
                "  :!:    :!:  !:!      !:!   :!:  !:!     :!:     :!:  :!:  !:!  :!:  !:!  :!:  !:!  :!:   !::  :!:       :!:  !:!  \n" +
                "   ::    ::   :::  :::: ::    ::  :::     :::     ::   ::   :::   ::   ::  ::   :::   ::: ::::   :: ::::  ::   :::  \n" +
                "   :      :   : :  :: : :     :   :::      :      :     :   : :  ::    :    :   : :   :: :: :   : :: ::    :   : : ";
        System.out.println(art);
        System.out.print("\n");
        System.out.println(RED_BOLD_BRIGHT + "         ***************************       ");
        System.out.println(RED_BOLD_BRIGHT + "         | WELCOME in TASK MANAGER |       ");
        System.out.println(RED_BOLD_BRIGHT + "         ***************************       ");
        System.out.print("\n");
        userController.login();
        while (User.getUserConnect() == null)
        {
            System.out.println("\n");
            userController.login();
        }

        if ("admin".equalsIgnoreCase(User.getUserConnect().getRole()))
        {
            serviceAdmin.choixAdmin();
        } else {

            serviceUser.choixUser();

        }
    }
}