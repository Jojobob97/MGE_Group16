package ch.ost.rj.mge.tasktracker.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ch.ost.rj.mge.tasktracker.R;
import ch.ost.rj.mge.tasktracker.activities.EditActivity;
import ch.ost.rj.mge.tasktracker.activities.OverviewActivity;

public class DetailButtonsFragment extends Fragment {

    private Button editBtn;
    private Button deleteBtn;

    public static DetailButtonsFragment create() { return new DetailButtonsFragment(); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_detail_buttons, container, false);
        editBtn = fragment.findViewById(R.id.detail_button_edit);
        deleteBtn = fragment.findViewById(R.id.detail_button_delete);

        editBtn.setOnClickListener(v -> {
            Intent intent = EditActivity.createIntent(this.getContext());
            startActivity(intent);
        });

        deleteBtn.setOnClickListener(v -> {
            Intent intent = OverviewActivity.createIntent(this.getContext());
            //ask before delete and start activity
            startActivity(intent);
        });

        return fragment;
    }
}