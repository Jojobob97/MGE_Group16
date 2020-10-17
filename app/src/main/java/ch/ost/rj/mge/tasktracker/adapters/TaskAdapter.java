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

import ch.ost.rj.mge.tasktracker.R;
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
                R.layout.item_task,
                parent,
                false);

        TextView titleTextView = view.findViewById(R.id.task_title);
        TextView effortTextView = view.findViewById(R.id.task_information);
        TextView progressTextView = view.findViewById(R.id.task_progress);

        return new TaskViewHolder(view, titleTextView, effortTextView, progressTextView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = this.tasks.get(position);
        holder.titleTextView.setText(task.getTitle());
        holder.effortTextView.setText("Actual: " + task.getActualEffort() +", Target: " + task.getTargetEffort());
        int progress = (int)((100 / task.getTargetEffort()) * task.getActualEffort());
        holder.progressTextView.setText(String.valueOf(progress) + "%");
    }

    @Override
    public int getItemCount() {
        return this.tasks.size();
    }
}
