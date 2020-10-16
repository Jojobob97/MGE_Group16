package ch.ost.rj.mge.tasktracker.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ch.ost.rj.mge.tasktracker.R;

public class DetailEffortFragment extends Fragment {

    private TextView detailTargetText;
    private TextView detailActualText;
    double taskTargetTime;
    double taskActualTime;

    public DetailEffortFragment(double taskTargetTime, double taskActualTime) {
        this.taskActualTime = taskActualTime;
        this.taskTargetTime = taskTargetTime;
    }

    public static DetailEffortFragment create(double taskTargetTime, double taskActualTime) {
        return new DetailEffortFragment(taskTargetTime, taskActualTime);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_detail_effort, container, false);
        detailTargetText = fragment.findViewById(R.id.detail_effort_target);
        detailActualText = fragment.findViewById(R.id.detail_effort_actual);
        int targetHours = (int) taskTargetTime;
        int targetMinutes = (int) (taskTargetTime*60) - targetHours*60;
        int actualHours = (int) taskActualTime;
        int actualMinutes = (int) (taskActualTime*60) - actualHours*60;
        //System.out.println("test " + taskActualTime*60);
        String target = "Target: " + targetHours + "h " + targetMinutes + "min";
        String actual = "Actual: " + actualHours + "h " + actualMinutes + "min";

        detailTargetText.setText(target);
        detailActualText.setText(actual);

        return fragment;
    }
}