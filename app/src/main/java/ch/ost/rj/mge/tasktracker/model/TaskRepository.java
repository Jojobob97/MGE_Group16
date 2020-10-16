package ch.ost.rj.mge.tasktracker.model;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private static ArrayList<Task> tasks;
    public static List<Task> taskSelect;

    static {
        tasks = new ArrayList<>();

        tasks.add(new Task("MGE", 420.5, 36.0, false));
        tasks.add(new Task("WED2", 104.5, 36.0, false));
        tasks.add(new Task("ICTH", 104.5, 36.0, false));
        tasks.add(new Task("CPP", 104.5, 36.0 , false));
        tasks.add(new Task("MSTE", 104.5, 36.0, false));
        tasks.add(new Task("MGE", 104.5, 36.0 , false));
        tasks.add(new Task("WED2", 104.5, 36.0, false));
        tasks.add(new Task("ICTH", 104.5, 36.0, false));
        tasks.add(new Task("CPP", 104.5, 36.0 , false));
        tasks.add(new Task("MSTE", 104.5, 36.0, false));
        tasks.add(new Task("MGE", 104.5, 36.0 , false));
        tasks.add(new Task("WED2", 104.5, 36.0, false));
        tasks.add(new Task("ICTH", 104.5, 36.0, false));
        tasks.add(new Task("CPP", 104.5, 36.0 , false));
        tasks.add(new Task("MSTE", 104.5, 36.0, false));
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    public static void addTask(Task task) {
        tasks.add(task);
    }
}
