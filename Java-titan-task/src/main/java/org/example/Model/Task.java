package org.example.Model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;

public class Task {
    private int id ;
    private String code ;
    private String libelle ;
    private Priority priority ;
    private Categorie categorie ;
    private User user ;
    private LocalDate date_creation ;

    private static HashMap<String,Task> tasks = new HashMap<>();

    public Task(int id,String code, String libelle, Priority priority, Categorie categorie, User user, LocalDate date_creation)
    {
        this.id = id;
        this.code = code;
        this.libelle = libelle;
        this.priority = priority;
        this.categorie = categorie;
        this.user = user;
        this.date_creation = date_creation;
        tasks.put(this.code,this);
    }

    public Task(String code, String libelle, Priority priority, Categorie categorie, User user, LocalDate date_creation)
    {
        this.code = code;
        this.libelle = libelle;
        this.priority = priority;
        this.categorie = categorie;
        this.user = user;
        this.date_creation = date_creation;
        tasks.put(this.code,this);
    }

    public Task() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(LocalDate date_creation) {
        this.date_creation = date_creation;
    }

    public static HashMap<String, Task> getTasks() {
        return tasks;
    }

    public static void setTasks(HashMap<String, Task> tasks) {
        Task.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", libelle='" + libelle + '\'' +
                ", priority=" + priority +
                ", categorie=" + categorie +
                ", user=" + user +
                ", date_creation=" + date_creation +
                '}';
    }
}
