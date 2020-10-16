package ch.ost.rj.mge.tasktracker.services;

import android.content.Context;
import android.os.HandlerThread;
import android.util.Log;

import androidx.room.Dao;
import androidx.room.Room;

import java.util.List;

import ch.ost.rj.mge.tasktracker.database.TaskDao;
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
        List<Task> result = null;

        Runnable read = () -> {
            TaskDatabase db = Room.databaseBuilder(context, TaskDatabase.class, ROOM_DB).build();
            List<Task> tasks = db.taskDao().getTasks();
            for (Task task : tasks) {
                Log.d("DB", "DB Entry | " + task.id + " | " + task.title + " | " + task.actualEffort + " | " + task.targetEffort + " | " + task.trackingState);
            }
            TaskRepository.taskSelect = tasks;

            db.close();
        };
        new Thread(read).start();

        return TaskRepository.getTasks();
    }
}
