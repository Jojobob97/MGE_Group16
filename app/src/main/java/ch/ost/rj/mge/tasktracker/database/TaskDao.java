package ch.ost.rj.mge.tasktracker.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ch.ost.rj.mge.tasktracker.model.Task;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    List<Task> getTasks();

    @Insert
    void insert(Task task);

    @Delete
    void delete(Task task);
}
