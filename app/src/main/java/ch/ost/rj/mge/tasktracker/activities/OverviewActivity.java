package ch.ost.rj.mge.tasktracker.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ch.ost.rj.mge.tasktracker.R;
import ch.ost.rj.mge.tasktracker.adapters.TaskAdapter;
import ch.ost.rj.mge.tasktracker.adapters.TaskViewHolder;
import ch.ost.rj.mge.tasktracker.model.TaskRepository;

public class OverviewActivity extends AppCompatActivity {
    FloatingActionButton floating_new_btn;
    Button toDetailBtn;
    RecyclerView.Adapter<TaskViewHolder> adapter;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, OverviewActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new TaskAdapter(TaskRepository.getTasks());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        RecyclerView recyclerView = findViewById(R.id.overview_tasks);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(dividerItemDecoration);

        floating_new_btn = findViewById(R.id.floating_action_button_new);
        toDetailBtn = findViewById(R.id.toDetailButton);

        floating_new_btn.setOnClickListener(v -> {
            Intent intent = EditActivity.createIntent(this);
            startActivity(intent);
        });

        toDetailBtn.setOnClickListener(v -> {
            Intent intent = DetailActivity.createIntent(this);
            startActivity(intent);
        });
    }
}