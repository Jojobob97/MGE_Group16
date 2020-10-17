package ch.ost.rj.mge.tasktracker.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import ch.ost.rj.mge.tasktracker.R;
import ch.ost.rj.mge.tasktracker.fragments.DetailButtonsFragment;
import ch.ost.rj.mge.tasktracker.fragments.DetailButtonsFragmentCallback;
import ch.ost.rj.mge.tasktracker.fragments.DetailEffortFragment;
import ch.ost.rj.mge.tasktracker.fragments.DetailStartFragment;
import ch.ost.rj.mge.tasktracker.fragments.DetailStartFragmentCallback;
import ch.ost.rj.mge.tasktracker.fragments.DetailTimerFragment;
import ch.ost.rj.mge.tasktracker.fragments.DetailTimerFragmentCallback;
import ch.ost.rj.mge.tasktracker.model.Task;
import ch.ost.rj.mge.tasktracker.services.TaskService;

public class DetailActivity extends AppCompatActivity implements DetailStartFragmentCallback, DetailTimerFragmentCallback, DetailButtonsFragmentCallback {

    private DetailEffortFragment detailEffortFragment;
    private DetailStartFragment detailStartFragment;
    private DetailTimerFragment detailTimerFragment;
    private DetailButtonsFragment detailButtonsFragment;
    private ActionBar actionBar;
    private boolean initialTaskRunning;
    private double taskActionTime;
    private double taskTargetTime;
    private int taskId;
    private Task task;


    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, DetailActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        taskId = intent.getIntExtra("taskId", 0);
        task = TaskService.selectSingleTask(this, taskId);

        initialTaskRunning = false;
        taskActionTime = task.getActualEffort();
        taskTargetTime = task.getTargetEffort();

        //Add Back Button to ActionBar - needs @Override onOptionsItemSelected to navigate to back
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        detailEffortFragment = DetailEffortFragment.create(taskTargetTime, taskActionTime);
        detailStartFragment = DetailStartFragment.create(initialTaskRunning);
        detailTimerFragment = DetailTimerFragment.create();
        detailButtonsFragment = DetailButtonsFragment.create();
        getSupportFragmentManager()
            .beginTransaction()
            .add(R.id.detail_effort_container, detailEffortFragment)
            .add(R.id.detail_timer_container, detailTimerFragment)
            .add(R.id.detail_start_container, detailStartFragment)
            .add(R.id.detail_buttons_container, detailButtonsFragment)
            .commit();

        if (initialTaskRunning) {
            getSupportFragmentManager()
                .beginTransaction()
                .remove(detailEffortFragment)
                .remove(detailButtonsFragment)
                .commit();
        }
        else {
            getSupportFragmentManager()
                .beginTransaction()
                .remove(detailTimerFragment)
                .commit();
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

    @Override
    public void onSwitchChange(boolean newSwitchState) {
        System.out.println("Callback SwitchChange");
        actionBar.setDisplayHomeAsUpEnabled(!newSwitchState); //hides/shows home button of action bar

        initialTaskRunning = newSwitchState;
        if (initialTaskRunning) {
            getSupportFragmentManager()
                .beginTransaction()
                .remove(detailEffortFragment)
                .remove(detailButtonsFragment)
                .add(R.id.detail_timer_container, detailTimerFragment)
                .commit();
        }
        else {
            getSupportFragmentManager()
                .beginTransaction()
                .remove(detailTimerFragment)
                .commitNow(); //CommitNow because Callback of detailTimerFragment has to update taskActionTime
            detailEffortFragment = DetailEffortFragment.create(taskTargetTime, taskActionTime);
            getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.detail_effort_container, detailEffortFragment)
                .add(R.id.detail_buttons_container, detailButtonsFragment)
                .commit();
        }
    }

    @Override
    public void safeChronometer(double time) {
        taskActionTime += time;
        task.setActualEffort(taskActionTime);
        TaskService.updateTask(this, task);
    }

    @Override
    public void deleteTask() {
        TaskService.deleteTask(this, task);
    }

}