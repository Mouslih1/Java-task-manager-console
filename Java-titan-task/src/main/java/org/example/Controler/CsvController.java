package org.example.Controler;

import org.example.DAO.TaskDAO;
import org.example.Model.Task;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class CsvController {

    public static void transfererInCsv()
    {
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("TacheCSV.csv"),StandardCharsets.UTF_8));
            StringBuilder stringBuilder;

            for (Task t : Task.getTasks().values())
            {
                stringBuilder = new StringBuilder();
                stringBuilder.append(t.getId()).append(",");
                stringBuilder.append(t.getCode()).append(",");
                stringBuilder.append(t.getLibelle()).append(",");
                stringBuilder.append(t.getPriority()).append(",");
                stringBuilder.append(t.getCategorie()).append(",");
                stringBuilder.append(t.getUser()).append(",");
                stringBuilder.append(t.getDate_creation());
                bufferedWriter.write(stringBuilder.toString());
                bufferedWriter.newLine();
            }

            bufferedWriter.flush();
            bufferedWriter.close();
            System.out.println("Tous les taches transfer dans le fichier CSV");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveInCsv()
    {
        String line;
        try{
            FileReader fileReader = new FileReader("TacheCSV.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] l = line.split(",");
                String t = l[1];
                Task task = TaskDAO.searchTaskByCode(t);
                assert task != null;
                Task o = new Task(task.getCode(),task.getLibelle(),task.getPriority(),task.getCategorie(),task.getUser(),task.getDate_creation());
                Task.getTasks().put(o.getCode(), o);
            }

            bufferedReader.close();
            System.out.println("Tous les taches save dans le fichier csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
