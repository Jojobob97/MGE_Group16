package ch.ost.rj.mge.tasktracker.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class TaskViewHolder extends RecyclerView.ViewHolder {
    public TextView titleTextView;
    public TextView effortTextView;
    public TextView progressTextView;

    public TaskViewHolder(View parent, TextView titleTextView, TextView effortTextView, TextView progressTextView) {
        super(parent);
        this.titleTextView = titleTextView;
        this.effortTextView = effortTextView;
        this.progressTextView = progressTextView;
    }
}