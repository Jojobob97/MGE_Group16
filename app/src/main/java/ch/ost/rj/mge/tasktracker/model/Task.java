package ch.ost.rj.mge.tasktracker.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "targetEffort")
    public double targetEffort;
    @ColumnInfo(name = "actualEffort")
    public double actualEffort;
    @ColumnInfo(name = "trackingState")
    public boolean trackingState;

    public Task(String title, double targetEffort, double actualEffort, boolean trackingState) {
        this.title = title;
        this.targetEffort = targetEffort;
        this.actualEffort = actualEffort;
        this.trackingState = trackingState;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getTargetEffort() {
        return targetEffort;
    }

    public void setTargetEffort(double targetEffort) {
        this.targetEffort = targetEffort;
    }

    public double getActualEffort() {
        return actualEffort;
    }

    public void setActualEffort(double actualEffort) {
        this.actualEffort = actualEffort;
    }
}
