package ch.ost.rj.mge.tasktracker.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Objects;

import ch.ost.rj.mge.tasktracker.R;

public class DetailButtonsFragment extends Fragment {

    private Button editBtn;
    private Button deleteBtn;
    private DetailButtonsFragmentCallback callback;

    public static DetailButtonsFragment create() { return new DetailButtonsFragment(); }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            callback = (DetailButtonsFragmentCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Owner activity must implement LoginSubmitFragmentCallback.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_detail_buttons, container, false);
        editBtn = fragment.findViewById(R.id.detail_button_edit);
        deleteBtn = fragment.findViewById(R.id.detail_button_delete);

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(Objects.requireNonNull(this.getContext()));
        alertBuilder.setMessage("Are you sure to delete this task?\nThis action can't be undone.");
        alertBuilder.setCancelable(true);

        alertBuilder.setPositiveButton(
                "DELETE",
                (dialog, id) -> {
                    dialog.cancel();
                    callback.deleteTask();
                });
        alertBuilder.setNegativeButton(
                "CANCEL",
                (dialog, id) -> dialog.cancel());
        AlertDialog alert = alertBuilder.create();


        editBtn.setOnClickListener(v -> {
            callback.editTask();
        });

        deleteBtn.setOnClickListener(v -> {
            alert.show();
        });

        return fragment;
    }
}