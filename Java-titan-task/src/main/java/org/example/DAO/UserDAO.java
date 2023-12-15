package org.example.DAO;

import org.example.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    static Connection connection = DatabaseConnection.getConnection();
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";



    public static void addDBO(User user)
    {
        try {
            String insertQuery = "INSERT INTO users (login , password , role) VALUES (?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());

            preparedStatement.executeUpdate();

            System.out.print(GREEN + "SUCCESS -> ");
            System.out.println(GREEN_BOLD_BRIGHT + "user inserted into the database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error inserting user into the database.");
        }
    }

    public static void deleteDOAById(int id)
    {
        try {
            String deleteQuery = "DELETE FROM users WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();


            if (rowsAffected > 0) {
                System.out.print(GREEN + "SUCCESS -> ");
                System.out.println(GREEN_BOLD_BRIGHT + "User with id '" + id + "' deleted successfully.");
            } else {
                System.out.println(RED + "FAIL -> ");
                System.out.println(RED_BOLD_BRIGHT + "User with id '" + id + "' not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting user from the database.");
        }

    }

    public static void updateDOAById(int id, User userUpdate)
    {
        try {
            if (userExist(id))
            {
                String updateQuery = "UPDATE users SET login = ?, password = ?, role = ? WHERE id = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

                preparedStatement.setString(1, userUpdate.getLogin());
                preparedStatement.setString(2, userUpdate.getPassword());
                preparedStatement.setString(3, userUpdate.getRole());
                preparedStatement.setInt(4, id);

                preparedStatement.executeUpdate();

                System.out.print(GREEN + "SUCCESS -> ");
                System.out.println(GREEN_BOLD_BRIGHT + "User with id '" + id + "' updated successfully.");
            } else {
                System.out.println(RED + "FAIL -> ");
                System.out.println(RED_BOLD_BRIGHT + "User with id '" + id + "' not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating contact in the database.");
        }
    }

    public static boolean userExist(int id) throws SQLException
    {
        String checkQuery = "SELECT COUNT(*) FROM users WHERE id = ?";
        PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
        checkStatement.setInt(1, id);

        ResultSet resultSet = checkStatement.executeQuery();
        resultSet.next();

        int count = resultSet.getInt(1);

        return count > 0;
    }

    public static void getAll()
    {
        try {
            String selectQuery = "SELECT * FROM users";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                User user = new User(resultSet.getInt("id"),resultSet.getString("login"),resultSet.getString("password"),resultSet.getString("role"));
                System.out.println(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error show users from the database.");
        }
    }

    public static User searchDOAById(int id)
    {
        try {
            String selectQuery = "SELECT * FROM users WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            User user = null;
            if (resultSet.next())
            {
                user = new User(resultSet.getInt("id"),resultSet.getString("login"),resultSet.getString("password"),resultSet.getString("role"));
            }else{
                System.out.print(GREEN + "SUCCESS -> ");
                System.out.println(GREEN_BOLD_BRIGHT + "No user with this id "+ id);
            }

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error searching for user in the database.");
        }
        return null;
    }

    public static User login(String login , String motPass)
    {
        try {
            String selectQuery = "SELECT * FROM users WHERE login = ? AND password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, motPass);
            ResultSet resultSet = preparedStatement.executeQuery();

            User user = null;
            if (resultSet.next())
            {
                user = new User(resultSet.getInt("id"),resultSet.getString("login"),resultSet.getString("password"),resultSet.getString("role"));
                System.out.print(GREEN + "SUCCESS -> ");
                System.out.println(GREEN_BOLD_BRIGHT + "Login with success");
            }else{
                System.out.print(RED + "FAIL -> ");
                System.out.println(RED_BOLD_BRIGHT + "Wrong login or password !");
            }

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
