package ch.ost.rj.mge.tasktracker.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import ch.ost.rj.mge.tasktracker.R;
import ch.ost.rj.mge.tasktracker.model.Task;
import ch.ost.rj.mge.tasktracker.services.TaskService;

public class EditActivity extends AppCompatActivity {
    EditText title;
    EditText targetEffort;
    Button saveBtn;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, EditActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        saveBtn = findViewById(R.id.editSaveBtn);
        saveBtn.setOnClickListener(v -> {
            title = findViewById(R.id.editTitleInput);
            targetEffort = findViewById(R.id.editTargetInput);
            Task task = new Task(title.getText().toString(), Double.parseDouble(targetEffort.getText().toString()), 0, false);
            TaskService.insertTask(this, task);
            Intent intent = OverviewActivity.createIntent(this);
            startActivity(intent);
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