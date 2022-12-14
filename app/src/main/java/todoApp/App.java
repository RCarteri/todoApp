/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package todoApp;

import controller.ProjectController;
import controller.TaskController;
import model.Project;
import model.Task;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static util.ConnectionFactory.closeConnection;
import static util.ConnectionFactory.getConnection;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) throws SQLException {
                ProjectController projectController = new ProjectController();
        Project project = new Project();
        project.setName("projeto2");
        project.setId(1);
        project.setDescription("descricao");
        projectController.update(project);

//        ProjectController projectController = new ProjectController();
//        Project project = new Project();
//        project.setName("projeto2");
//        project.setDescription("descricao");
//        projectController.save(project);
//
//        ProjectController projectController2 = new ProjectController();
//        Project project2 = new Project();
//        project2.setId(1);
//        project2.setName("projeto novo");
//        project2.setDescription("descricao");
//        projectController2.save(project2);
//
//        List<Project> projects = projectController2.getAll();
//        System.out.println("Total de projetos = " + projects);
//
//        TaskController taskController = new TaskController();
//        Task task = new Task();
//        task.setName("Tarefa");
//        task.setDescription("Descrição");
//        task.setIsCompleted(false);
//        task.setNotes("nota");
//        task.setDeadline(new Date());
//        task.setIdProjeto(1);
//        taskController.save(task);
//
//        List<Task> tasks = taskController.getAll(1);
//        System.out.println("Total de projetos = " + projects);
    }
}
