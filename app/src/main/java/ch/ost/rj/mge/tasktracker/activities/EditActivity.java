package ch.ost.rj.mge.tasktracker.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ch.ost.rj.mge.tasktracker.R;
import ch.ost.rj.mge.tasktracker.model.Task;
import ch.ost.rj.mge.tasktracker.services.TaskService;

public class EditActivity extends AppCompatActivity {
    TextView activityTitle;
    EditText title;
    EditText targetEffort;
    Button saveBtn;
    int currentTaskId = 0;
    Task editTask;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, EditActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        activityTitle = findViewById(R.id.activityTitle);
        title = findViewById(R.id.editTitleInput);
        Intent intent = getIntent();
        currentTaskId = intent.getIntExtra("taskId", 0);
        targetEffort = findViewById(R.id.editTargetInput);
        if(currentTaskId != 0) {
            activityTitle.setText(R.string.activity_title_edit);
            editTask = TaskService.selectSingleTask(this, currentTaskId);
            title.setText(editTask.title);
            targetEffort.setText(String.valueOf(editTask.targetEffort));
        }

        saveBtn = findViewById(R.id.editSaveBtn);
        saveBtn.setOnClickListener(v -> {
            if(currentTaskId == 0) {
                Task newTask = new Task(title.getText().toString(), Double.parseDouble(targetEffort.getText().toString()), 0, false);
                TaskService.insertTask(this, newTask);
            } else {
                editTask.setTitle(title.getText().toString());
                editTask.setTargetEffort(Double.valueOf(targetEffort.getText().toString()));
                TaskService.updateTask(this, editTask);
            }
            Intent intentOverview = OverviewActivity.createIntent(this);
            startActivity(intentOverview);
        });

        //Add Back Button to ActionBar - needs @Override onOptionsItemSelected to navigate to back
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}