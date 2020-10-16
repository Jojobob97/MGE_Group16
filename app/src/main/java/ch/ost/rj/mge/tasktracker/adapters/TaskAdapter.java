package ch.ost.rj.mge.tasktracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ch.ost.rj.mge.tasktracker.model.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {
    private final List<Task> tasks;

    public TaskAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(
                android.R.layout.simple_list_item_2,
                parent,
                false);

        TextView titleTextView = view.findViewById(android.R.id.text1);
        TextView effortTextView = view.findViewById(android.R.id.text2);

        return new TaskViewHolder(view, titleTextView, effortTextView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = this.tasks.get(position);
        holder.titleTextView.setText(task.getTitle());
        holder.effortTextView.setText("Actual: " + task.getActualEffort() +", Target: " + task.getTargetEffort());
    }

    @Override
    public int getItemCount() {
        return this.tasks.size();
    }
}
