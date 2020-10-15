package ch.ost.rj.mge.tasktracker.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import ch.ost.rj.mge.tasktracker.R;

public class OverviewActivity extends AppCompatActivity {
    Button toEditBtn;
    Button toDetailBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        toEditBtn = findViewById(R.id.toEditButton);
        toDetailBtn = findViewById(R.id.toDetailButton);

        toEditBtn.setOnClickListener(v -> {
            Intent intent = EditActivity.createIntent(this);
            startActivity(intent);
        });

        toDetailBtn.setOnClickListener(v -> {
            Intent intent = DetailActivity.createIntent(this);
            startActivity(intent);
        });
    }
}