package org.example.Controler;

import org.example.DAO.CategorieDAO;
import org.example.Model.Categorie;
import org.example.Model.User;

import java.sql.SQLException;
import java.util.Scanner;

public class CategorieController implements InterfaceController {
    Scanner scanner = new Scanner(System.in);
    public static final String RED = "\033[0;31m";
    public static final String PURPLE = "\033[0;35m";
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";
    @Override
    public void add() throws SQLException
    {
        Categorie categorie = saisie();
        CategorieDAO.addDBO(categorie);
    }

    @Override
    public void update() throws SQLException
    {
        Categorie c = saisieForDeleteAndUpdate();
        Categorie id = CategorieDAO.searchDAOByLibelle(c.getLibelle());
        System.out.println("Entrer nouveau libelle de catégorie:");
        String nouveauLibelle = scanner.next();
        Categorie categorie = new Categorie(id.getId(), nouveauLibelle);
        CategorieDAO.updateDBOByLibelle(c.getLibelle(),categorie);
    }

    @Override
    public void delete()
    {
        Categorie categorie = saisieForDeleteAndUpdate();
        CategorieDAO.deleteDOAByCode(categorie.getLibelle());
    }

    @Override
    public void getAll()
    {
        CategorieDAO.getAll();
    }

    @Override
    public Categorie saisie() throws SQLException
    {
        System.out.print("\n");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  ");
        System.out.println(PURPLE + "category libelle:");
        String libelle = scanner.next();
        while (CategorieDAO.categorieExist(libelle))
        {
            System.out.println(PURPLE + "this category already exist ! ");
            System.out.println(PURPLE + "-----------------------------");
            System.out.print("\n");
            System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  ");
            System.out.println(PURPLE + "category libelle:");
            libelle = scanner.next();
        }
        return new Categorie(libelle);
    }

    public Categorie saisieForDeleteAndUpdate()
    {
        System.out.print("\n");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  ");
        System.out.println(PURPLE + "libelle category:");
        String libelle = scanner.next();
        while (!CategorieDAO.categorieExist(libelle))
        {
            System.out.println(PURPLE + "this category not exist !");
            System.out.println("-----------------------------");
            System.out.print("\n");
            System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  ");
            System.out.println(PURPLE + "libelle category exist:");
            libelle = scanner.next();
        }
        return new Categorie(libelle);
    }
}
