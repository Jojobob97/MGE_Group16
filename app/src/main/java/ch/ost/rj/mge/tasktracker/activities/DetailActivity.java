package ch.ost.rj.mge.tasktracker.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import ch.ost.rj.mge.tasktracker.R;
import ch.ost.rj.mge.tasktracker.fragments.DetailButtonsFragment;
import ch.ost.rj.mge.tasktracker.fragments.DetailEffortFragment;
import ch.ost.rj.mge.tasktracker.fragments.DetailStartFragment;
import ch.ost.rj.mge.tasktracker.fragments.DetailStartFragmentCallback;
import ch.ost.rj.mge.tasktracker.fragments.DetailTimerFragment;

public class DetailActivity extends AppCompatActivity implements DetailStartFragmentCallback {

    private DetailEffortFragment detailEffortFragment;
    private DetailStartFragment detailStartFragment;
    private DetailTimerFragment detailTimerFragment;
    private DetailButtonsFragment detailButtonsFragment;
    private boolean initialTaskRunning;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, DetailActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Add Back Button to ActionBar - needs @Override onOptionsItemSelected to navigate to back
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initialTaskRunning = false; //Variable bei öffnen von Activity übergeben.

        detailEffortFragment = DetailEffortFragment.create();
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
        initialTaskRunning = newSwitchState;
        if (initialTaskRunning) {
            getSupportFragmentManager()
                .beginTransaction()
                .remove(detailEffortFragment)
                .add(R.id.detail_timer_container, detailTimerFragment)
                .commit();
        }
        else {
            getSupportFragmentManager()
                .beginTransaction()
                .remove(detailTimerFragment)
                .add(R.id.detail_effort_container, detailEffortFragment)
                .commit();
        }
    }


}