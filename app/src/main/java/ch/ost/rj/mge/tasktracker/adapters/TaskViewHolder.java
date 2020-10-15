package ch.ost.rj.mge.tasktracker.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class TaskViewHolder extends RecyclerView.ViewHolder {
    public TextView titleTextView;
    public TextView effortTextView;

    public TaskViewHolder(View parent, TextView titleTextView, TextView effortTextView) {
        super(parent);
        this.titleTextView = titleTextView;
        this.effortTextView = effortTextView;
    }
}