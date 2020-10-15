package ch.ost.rj.mge.tasktracker.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import ch.ost.rj.mge.tasktracker.R;
import ch.ost.rj.mge.tasktracker.fragments.DetailEffortFragment;
import ch.ost.rj.mge.tasktracker.fragments.DetailStartFragment;

public class DetailActivity extends AppCompatActivity {

    private DetailEffortFragment detailEffortFragment;
    private DetailStartFragment detailStartFragment;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, DetailActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailEffortFragment = DetailEffortFragment.create();
        detailStartFragment = DetailStartFragment.create();
        getSupportFragmentManager()
            .beginTransaction()
            .add(R.id.detail_effort_container, detailEffortFragment)
            .add(R.id.detail_start_container, detailStartFragment)
            .commit();

    }
}