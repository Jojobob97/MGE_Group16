package ch.ost.rj.mge.tasktracker.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ch.ost.rj.mge.tasktracker.R;

public class DetailEffortFragment extends Fragment {

    private String target;
    private String actual;

    public static DetailEffortFragment create() { return new DetailEffortFragment(); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_detail_effort, container, false);
        return fragment;
    }
}