package ch.ost.rj.mge.tasktracker.services;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import java.util.Collections;
import java.util.List;

import ch.ost.rj.mge.tasktracker.database.TaskDatabase;
import ch.ost.rj.mge.tasktracker.model.Task;
import ch.ost.rj.mge.tasktracker.model.TaskRepository;

public class TaskService {
    private static final String ROOM_DB = "room.db";

    public static void insertTask(Context context, Task task) {
        Log.d("DB", "insertTasks()");
        Runnable write = () -> {
            TaskDatabase db = Room.databaseBuilder(context, TaskDatabase.class, ROOM_DB).build();
            db.taskDao().insert(task);
            Log.d("DB", "DB Entry inserted");
            db.close();
        };
        new Thread(write).start();
    }

    public static List<Task> selectTasks(Context context) {
        Log.d("DB", "selectTasks()");
        TaskDatabase db = Room.databaseBuilder(context, TaskDatabase.class, ROOM_DB).allowMainThreadQueries().build();
        List<Task> tasks = db.taskDao().getTasks();
        db.close();
        Collections.reverse(tasks);

        return tasks;
    }

    public static Task selectSingleTask(Context context, int taskId) {
        TaskDatabase db = Room.databaseBuilder(context, TaskDatabase.class, ROOM_DB).allowMainThreadQueries().build();
        Task task = db.taskDao().getSingleTask(taskId);
        db.close();
        return task;
    }
}
