package org.example;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TaskCLi {
    public static void main(String[] args){
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        Set<String> set = new HashSet<>();
        set.add("done");
        set.add("todo");
        set.add("in-progress");

        while(true){
            String command = scanner.next();
            switch(command){
                case "add":
                    String description = scanner.nextLine().trim();
                    taskManager.addTask(description);
                    break;
                case "update":
                    int id = scanner.nextInt();
                    String desc = scanner.nextLine();
                    taskManager.updateTasks(id, desc);
                    break;
                case "delete":
                    int idRemove = scanner.nextInt();
                    taskManager.removeTask(idRemove);
                    break;
                case "mark-in-progress":
                    int idMark = scanner.nextInt();
                    taskManager.markTask("in-progress", idMark);
                    break;
                case "mark-todo":
                    int idMarkToDo = scanner.nextInt();
                    taskManager.markTask("todo", idMarkToDo);
                    break;
                case "mark-done":
                    int idMarkDone = scanner.nextInt();
                    taskManager.markTask("done", idMarkDone);
                    break;
                case "list":
                    String s = scanner.nextLine().trim();
                    if(set.contains(s)){
                        taskManager.listByStatus(s);
                    }
                    else {
                        taskManager.listTasks();
                    }
                    break;
                case "exit":
                    System.out.println("Exiting");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid Command");
                    break;
            }
        }
    }
}
