package ch.ost.rj.mge.tasktracker.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import ch.ost.rj.mge.tasktracker.R;
import ch.ost.rj.mge.tasktracker.adapters.RecyclerItemClickListener;
import ch.ost.rj.mge.tasktracker.adapters.TaskAdapter;
import ch.ost.rj.mge.tasktracker.adapters.TaskViewHolder;
import ch.ost.rj.mge.tasktracker.model.Task;
import ch.ost.rj.mge.tasktracker.services.TaskService;

public class OverviewActivity extends AppCompatActivity {
    FloatingActionButton floating_new_btn;
    RecyclerView.Adapter<TaskViewHolder> adapter;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, OverviewActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        Context context = this;
        Intent detailTaskIntent = DetailActivity.createIntent(context);

        RecyclerView recyclerView = findViewById(R.id.overview_tasks);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        List<Task> tasks = TaskService.selectTasks(context);
                        Task clicked = tasks.get(position);
                        int id = clicked.getId();
                        detailTaskIntent.putExtra("taskId", id);
                        startActivity(detailTaskIntent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        renderOverview(this, recyclerView);

        floating_new_btn = findViewById(R.id.floating_action_button_new);

        floating_new_btn.setOnClickListener(v -> {
            Intent intent = EditActivity.createIntent(this);
            startActivity(intent);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.overview_tasks);
        renderOverview(this, recyclerView);

    }

    public void renderOverview(Context context, RecyclerView recyclerView) {
        adapter = new TaskAdapter(TaskService.selectTasks(this));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

}