package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static final String FILE_NAME = "tasks.json";
    private ObjectMapper objectMapper;
    private List<Task> tasks;

    public TaskManager() {
        objectMapper = new ObjectMapper();
        tasks = loadTask();
    }

    private List<Task> loadTask(){
        File file = new File(FILE_NAME);
        if(file.exists()){
            try {
                return List.of(objectMapper.readValue(file, Task[].class));
            } catch (IOException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }
        return new ArrayList<>();
    }

    public void saveTask(){
        try{
            objectMapper.writeValue(new File(FILE_NAME), tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addTask(String description){
        int id = tasks.size()+1;
        tasks.add(new Task(id, description));
        saveTask();
        System.out.println("Task added successfully (ID: " + id + ")");
    }

    public void updateTasks(int id, String description){
        for(Task task : tasks){
            if(task.getId() == id)
                task.setDescription(description);
        }
        saveTask();
    }

    public void removeTask(int id){
        tasks.removeIf(task -> task.getId() == id);
        saveTask();
    }

    public void listTasks(){
        if(tasks.isEmpty()) {
            System.out.println("The list are empty");
            return;
        }
        for(Task task : tasks){
            System.out.println(task.getId() + " - " + task.getDescription() + " - " + task.getStatus());
        }

    }

    public void listByStatus(String status){
        for(Task task : tasks){
            if(task.getStatus().equals(status)){
                System.out.println(task.getId() + " - " + task.getDescription() + " - " + task.getStatus());
                return;
            }
        }
        System.out.println("There's no task " + status);
    }

    public void markTask(String status, int id){
        for(Task task : tasks){
            if(task.getId() == id){
                task.setStatus(status);
                System.out.println("Status of task " + id + "marked succesfully as " + status);
                return;
            }
        }
        System.out.println("Task not found");
    }

}
