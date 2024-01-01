package org.example.DAO;


import org.example.Model.Categorie;
import org.example.Model.Priority;
import org.example.Model.Task;
import org.example.Model.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    static Connection connection = DatabaseConnection.getConnection();
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";

    public static void addDBO(Task task)
    {
        try{
            String query
                    = "insert into taches(code, "
                    + "nom,"+"priority,"+"id_categorie,"+"id_user,"+"date_creation, is_delete) VALUES (?, ?, ?, ?, ?, ?,0)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, task.getCode());
            ps.setString(2, task.getLibelle());
            ps.setString(3, task.getPriority().getName());
            ps.setInt(4, task.getCategorie().getId());
            ps.setInt(5, task.getUser().getId());
            ps.setDate(6, Date.valueOf(task.getDate_creation()));
            ps.executeUpdate();

            System.out.print(GREEN + "SUCCESS -> ");
            System.out.println(GREEN_BOLD_BRIGHT + "Task has been inserted successfully.");
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteDOAByCode(String code)
    {
        try{
            if(taskExist(code))
            {
                String query = "UPDATE taches " +
                        "SET is_delete = 1 " +
                        "WHERE code = ?";

                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, code);
                ps.executeUpdate();

                System.out.println("Task with code: '"+ code +"' delete successfully");
            }else{
                System.out.println("Task with code: '"+ code + "' not found");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void updateDBOByCode(String code, Task task)
    {
        try{
            if(taskExist(code))
            {
                String query = "UPDATE taches " +
                        "SET nom = ?, priority = ?, id_categorie = ?, id_user = ?, date_creation = ? " +
                        "WHERE code = ?";

                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, task.getLibelle());
                ps.setString(2, task.getPriority().getName());
                ps.setInt(3, task.getCategorie().getId());
                ps.setInt(4,task.getUser().getId());
                ps.setDate(5, Date.valueOf(task.getDate_creation()));
                ps.setString(6, code);
                ps.executeUpdate();

                System.out.print(GREEN + "SUCCESS -> ");
                System.out.println(GREEN_BOLD_BRIGHT + "Task with code: '"+ code +"' updated successfully");
            }else{
                System.out.print(RED + "FAIL -> ");
                System.out.println(RED_BOLD_BRIGHT + "Task with code: '"+ code + "' not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean taskExist(String code) throws SQLException
    {
        String checkQuery = "SELECT COUNT(*) FROM taches WHERE code = ?";
        PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
        checkStatement.setString(1, code);

        ResultSet resultSet = checkStatement.executeQuery();
        resultSet.next();

        int count = resultSet.getInt(1);

        return count > 0;
    }

    public static void getAll()
    {
        try{
            String query = "SELECT * FROM taches where is_delete = 0";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            Task task = null;
            while (rs.next())
            {
                Categorie categorie = CategorieDAO.searchDAOById(rs.getInt("id_categorie"));
                User user = UserDAO.searchDOAById(rs.getInt("id_user"));
                task = new Task(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getString("nom"),
                        Priority.valueOf(rs.getString("priority").toUpperCase()),
                        categorie,
                        user,
                        rs.getDate("date_creation").toLocalDate()
                );
                System.out.println(task);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Task searchTaskHistoryByCode(String code)
    {
        try {
            String selectQuery = "SELECT * FROM taches WHERE code = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();

            Task task = null;
            while (resultSet.next())
            {
                Categorie categorie = CategorieDAO.searchDAOById(resultSet.getInt("id_categorie"));
                User user = UserDAO.searchDOAById(resultSet.getInt("id_user"));
                task = new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("code"),
                        resultSet.getString("nom"),
                        Priority.valueOf(Priority.class, resultSet.getString("priority").toUpperCase()),
                        categorie,
                        user,
                        resultSet.getDate("date_creation").toLocalDate()
                );
                //task.getCategorie().getTasks().put(task.getCode(),task);
            }

            return task;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error searching for task in the database.");
        }
        return null;
    }

    public static Task searchTaskByCode(String code)
    {
        try {
            String selectQuery = "SELECT * FROM taches WHERE code = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();

            Task task = null;
            while (resultSet.next())
            {
                Categorie categorie = CategorieDAO.searchDAOById(resultSet.getInt("id_categorie"));
                User user = UserDAO.searchDOAById(resultSet.getInt("id_user"));
                task = new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("code"),
                        resultSet.getString("nom"),
                        Priority.valueOf(Priority.class, resultSet.getString("priority").toUpperCase()),
                        categorie,
                        user,
                        resultSet.getDate("date_creation").toLocalDate()
                );
                //task.getCategorie().getTasks().put(task.getCode(),task);
            }

            return task;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error searching for task in the database.");
        }
        return null;
    }

    public static List<Task> searchDOAByDate(LocalDate dateCreation)
    {
        try {
            String selectQuery = "SELECT * FROM taches WHERE date_creation = ? and is_delete = 0";

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setDate(1, Date.valueOf(dateCreation));
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println(GREEN + "SUCCESS -> ");
            System.out.println(GREEN_BOLD_BRIGHT + "Task with date '" + dateCreation + "':");

            List<Task> tasks = new ArrayList<>();
            while (resultSet.next())
            {
                Categorie categorie = CategorieDAO.searchDAOById(resultSet.getInt("id_categorie"));
                User user = UserDAO.searchDOAById(resultSet.getInt("id_user"));
                Task task = new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("code"),
                        resultSet.getString("nom"),
                        Priority.valueOf(resultSet.getString("priority").toUpperCase()),
                        categorie,
                        user,
                        resultSet.getDate("date_creation").toLocalDate()
                );
                tasks.add(task);
            }

            return tasks;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error searching for task in the database.");
        }
        return null;
    }

    public static List<Task> searchDBOByIdCategorie(int id)
    {
        try {
            String selectQuery = "SELECT * FROM taches WHERE id_categorie = ? and is_delete = 0";

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.print(GREEN + "SUCCESS -> ");
            System.out.println(GREEN_BOLD_BRIGHT + "Task with Categorie id '" + id + "':");

            List<Task> tasks = new ArrayList<>();
            while (resultSet.next())
            {
                Categorie categorie = CategorieDAO.searchDAOById(resultSet.getInt("id_categorie"));
                User user = UserDAO.searchDOAById(resultSet.getInt("id_user"));
                Task task = new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("code"),
                        resultSet.getString("nom"),
                        Priority.valueOf(resultSet.getString("priority").toUpperCase()),
                        categorie,
                        user,
                        resultSet.getDate("date_creation").toLocalDate()
                );
                tasks.add(task);
            }

            return tasks;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error searching for task in the database.");
        }
        return null;
    }


    public static void affecterTaskByCode(int id , String  code)
    {
        try{
            if(taskExist(code))
            {
                String query = "UPDATE taches " +
                        "SET  id_user = ? " +
                        "WHERE code = ? and is_delete = 0";

                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1,id);
                ps.setString(2, code);
                ps.executeUpdate();

                System.out.print(GREEN + "SUCCESS -> ");
                System.out.println(GREEN_BOLD_BRIGHT + "Task with code: '"+ code +"' affect a user id '" + id + "' successfully.");
            }else{
                System.out.print(RED + "SUCCESS -> ");
                System.out.println(RED_BOLD_BRIGHT + "Task with code: '"+ code + "' not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Task> searchDOAByPriority(String priority)
    {
        try {
            String selectQuery = "SELECT * FROM taches WHERE priority = ? and is_delete = 0";

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, priority);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println(GREEN + "SUCCESS -> ");
            System.out.println(GREEN_BOLD_BRIGHT + "Task with priority '" + priority + "':");

            List<Task> tasks = new ArrayList<>();
            while (resultSet.next())
            {
                Categorie categorie = CategorieDAO.searchDAOById(resultSet.getInt("id_categorie"));
                User user = UserDAO.searchDOAById(resultSet.getInt("id_user"));
                Task task = new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("code"),
                        resultSet.getString("nom"),
                        Priority.valueOf(resultSet.getString("priority").toUpperCase()),
                        categorie,
                        user,
                        resultSet.getDate("date_creation").toLocalDate()
                );
                tasks.add(task);
            }

            return tasks;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error searching for task in the database.");
        }
        return null;
    }
}
