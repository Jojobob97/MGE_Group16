package ch.ost.rj.mge.tasktracker.model;

public class Task {
    private String title;
    private double targetEffort;
    private double actualEffort;

    public Task(String title, double targetEffort, double actualEffort) {
        this.title = title;
        this.targetEffort = targetEffort;
        this.actualEffort = actualEffort;
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
