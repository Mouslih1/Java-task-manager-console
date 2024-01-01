package org.example.DAO;

import org.example.Model.Categorie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieDAO {
    static Connection connection = DatabaseConnection.getConnection();
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static void addDBO(Categorie categorie)
    {
        try {
            String query = "insert into categories(libelle) VALUES(?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, categorie.getLibelle());
            ps.executeUpdate();
            System.out.print(GREEN + "SUCCESS -> ");
            System.out.println(GREEN_BOLD_BRIGHT + "Category has been created successfully.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean categorieExist(String libelle)
    {
        String checkQuery = "SELECT COUNT(*) FROM categories WHERE libelle = ?";

        try {
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setString(1, libelle);
            ResultSet resultSet = checkStatement.executeQuery();
            resultSet.next();
            return resultSet.getBoolean(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteDOAByCode(String libelle)
    {
        try{
            String deleteQuery = "DELETE FROM categories WHERE libelle = ?";
            PreparedStatement ps = connection.prepareStatement(deleteQuery);
            ps.setString(1, libelle);
            int n = ps.executeUpdate();

            if (n > 0) {
                System.out.print(GREEN + "SUCCESS -> ");
                System.out.println(GREEN_BOLD_BRIGHT + "Category with code '" + libelle + "' deleted successfully.");
            } else {
                System.out.print(RED + "FAIL -> ");
                System.out.println(RED_BOLD_BRIGHT + "Category with code '" + libelle + "' not found.");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void updateDBOByLibelle(String libelle,Categorie categorie)
    {
        String udapteQuery = "UPDATE categories set libelle = ? WHERE libelle = ?";
        try {
            if (categorieExist(libelle))
            {
                PreparedStatement ps = connection.prepareStatement(udapteQuery);
                ps.setString(1, categorie.getLibelle());
                ps.setString(2, libelle);
                ps.executeUpdate();

                System.out.println(GREEN + "SUCCESS -> ");
                System.out.println(GREEN_BOLD_BRIGHT + "Category updated successfully");
            } else {
                System.out.println(RED + "FAIL -> ");
                System.out.println(RED_BOLD_BRIGHT + "Category not found");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getAll()
    {
        String getAllQuery = "SELECT * FROM categories";
        try {
            PreparedStatement ps = connection.prepareStatement(getAllQuery);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                Categorie categorie = new Categorie(rs.getInt("id"),rs.getString("libelle"));
                System.out.println(categorie);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Categorie searchDAOByLibelle(String libelle)
    {
        String searchQuery = "SELECT * FROM categories WHERE libelle = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
            preparedStatement.setString(1, libelle);
            ResultSet resultSet = preparedStatement.executeQuery();
            Categorie categorie = null;
            if(resultSet.next())
            {
                categorie = new Categorie(resultSet.getInt("id"),resultSet.getString("libelle"));
            }

            return categorie;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Categorie searchDAOById(int id)
    {
        String searchQuery = "SELECT * FROM categories WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Categorie categorie = null;
            if(resultSet.next())
            {
                categorie = new Categorie(resultSet.getInt("id"),resultSet.getString("libelle"));
            }

            return categorie;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
