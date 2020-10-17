package ch.ost.rj.mge.tasktracker.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import java.util.List;

import ch.ost.rj.mge.tasktracker.model.Task;

@Database(entities = {Task.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();


}
