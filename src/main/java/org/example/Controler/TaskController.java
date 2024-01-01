package org.example.Controler;

import org.example.DAO.CategorieDAO;
import org.example.DAO.TaskDAO;
import org.example.DAO.UserDAO;
import org.example.Model.Categorie;
import org.example.Model.Priority;
import org.example.Model.Task;
import org.example.Model.User;

import javax.sound.midi.Soundbank;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TaskController implements InterfaceController{
    Scanner scanner = new Scanner(System.in);
    public static final String RED = "\033[0;31m";
    public static final String PURPLE = "\033[0;35m";
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";
    
    @Override
    public void add() throws SQLException {
        Task task = saisie();
        TaskDAO.addDBO(task);
        task.getCategorie().getTasks().put(task.getCode(), task);
        String status = "Ajouter tache";
        if(TaskDAO.taskExist(task.getCode()))
        {
            HistoryController.addHistory(User.getUserConnect(),task,status,LocalDate.now());
        }else{
            System.out.print(RED + "FAIL -> ");
            System.out.println(RED_BOLD_BRIGHT + "this task is not found in database !");
        }
    }

    @Override
    public void update() throws SQLException 
    {
        Task task = saisie();
        TaskDAO.updateDBOByCode(task.getCode(), task);
        String status = "Modifier tache";
        if(TaskDAO.taskExist(task.getCode()))
        {
            HistoryController.addHistory(User.getUserConnect(),task,status,LocalDate.now());
        }else{
            System.out.println(RED + "FAIL -> ");
            System.out.println(RED_BOLD_BRIGHT + "this task is not found in database !");
        }
    }

    @Override
    public void delete()
    {
        System.out.print("\n");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  ");
        System.out.println(PURPLE_BOLD_BRIGHT + "task code:");
        String code = scanner.next();
        Task task  = TaskDAO.searchTaskByCode(code);
        TaskDAO.deleteDOAByCode(code);
        String status = "Supprimer tache";
        HistoryController.addHistory(User.getUserConnect(), task, status , LocalDate.now());
    }

    @Override
    public void getAll()
    {
        TaskDAO.getAll();
    }

    public void trieTaskWithDate()
    {
        System.out.print("\n");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  ");
        System.out.println(PURPLE + "search tasks by date:");
        LocalDate date = LocalDate.parse(scanner.next());
        List<Task> task = TaskDAO.searchDOAByDate(date);
        assert task != null;
        task.forEach(task1 -> System.out.println(task1.toString()));
    }

    public void trieTaskWithCategory()
    {
        System.out.print("\n");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  ");
        System.out.println(PURPLE + "search tasks by category:");
        int idCategorie = scanner.nextInt();
        List<Task> tasks = TaskDAO.searchDBOByIdCategorie(idCategorie);
        assert tasks != null;
        tasks.forEach(t -> System.out.println(t.toString()));
    }

    public void trieTaskWithPriority()
    {
        System.out.print("\n");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  ");
        System.out.print(PURPLE + "task priority ");
        System.out.println(PURPLE_BOLD_BRIGHT + "[LOW , MEDIUM , HIGH]:");
        String priorityChoice = scanner.next().toUpperCase();
        List<Task> task = TaskDAO.searchDOAByPriority(priorityChoice);
        assert task != null;
        task.forEach(t -> System.out.println(t.toString()));
    }

    @Override
    public Task saisie() throws SQLException
    {
        System.out.print("\n");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  ");
        System.out.println(PURPLE + "task code:");
        String code = scanner.next();
        if (User.getUserConnect() != null)
        {
            System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  ");
            System.out.println(PURPLE + "task libelle:");
            String libelle = scanner.next();
            System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  ");
            System.out.print(PURPLE + "task priority ");
            System.out.println(PURPLE_BOLD_BRIGHT + "[LOW , MEDIUM , HIGH]:");
            String priorityChoice = scanner.next().toUpperCase();
            Priority priority = Priority.valueOf(Priority.class, priorityChoice) ;
            System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  ");
            System.out.println(PURPLE + "category libelle:");
            String libelleCategorie = scanner.next();
            Categorie categorie = CategorieDAO.searchDAOByLibelle(libelleCategorie);
            return new Task(code, libelle,priority , categorie, User.getUserConnect(), LocalDate.now());
        }
        return null;
    }

    public void affecterTache()
    {
        System.out.print("\n");
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  ");
        System.out.println(PURPLE + "task code:");
        String code = scanner.next();
        Task task =TaskDAO.searchTaskByCode(code);
        System.out.print(PURPLE_BOLD_BRIGHT + "ENTER  ");
        System.out.println(PURPLE + "user id:");
        int id = scanner.nextInt();
        User user = UserDAO.searchDOAById(id);
        if (task != null) {
            task.setUser(user);
            assert user != null;
            TaskDAO.affecterTaskByCode(user.getId(), task.getCode());
        }
    }
}
