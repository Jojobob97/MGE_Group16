package ch.ost.rj.mge.tasktracker.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ch.ost.rj.mge.tasktracker.R;

public class DetailStartFragment extends Fragment {

    private SwitchCompat startSwitch;
    private boolean initialTaskRunning;
    private DetailStartFragmentCallback callback;

    public DetailStartFragment(boolean initialTaskRunning) {
        this.initialTaskRunning = initialTaskRunning;
    }

    public static DetailStartFragment create(boolean initialTaskRunning) {
        return new DetailStartFragment(initialTaskRunning);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            callback = (DetailStartFragmentCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Owner activity must implement LoginSubmitFragmentCallback.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_detail_start, container, false);

        startSwitch = fragment.findViewById(R.id.detail_start_switch);
        startSwitch.setChecked(initialTaskRunning);

        startSwitch.setOnClickListener(v -> {
            System.out.println("Callback");
            initialTaskRunning = !initialTaskRunning;
            callback.onSwitchChange(initialTaskRunning);
        });

        return fragment;
    }
}