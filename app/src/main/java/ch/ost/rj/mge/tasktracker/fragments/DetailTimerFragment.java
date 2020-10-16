package ch.ost.rj.mge.tasktracker.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;

import ch.ost.rj.mge.tasktracker.R;

public class DetailTimerFragment extends Fragment {

    private Chronometer detailTimerChronometer;
    private DetailTimerFragmentCallback callback;
    public static DetailTimerFragment create() { return new DetailTimerFragment(); }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            callback = (DetailTimerFragmentCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Owner activity must implement LoginSubmitFragmentCallback.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_detail_timer, container, false);
        detailTimerChronometer = fragment.findViewById(R.id.detail_timer_chronometer);
        //detailTimerChronometer.setBase(SystemClock.elapsedRealtime() - (0 * 60000 + 55 * 1000));
        detailTimerChronometer.start();

        return fragment;
    }

    @Override
    public void onPause() {
        System.out.println("Callback Timer");
        long time = SystemClock.elapsedRealtime() - detailTimerChronometer.getBase();
        int timeInSeconds = (int) time/1000;
        double timeInHours = (double) timeInSeconds/3600;
        callback.safeChronometer(timeInHours);
        super.onPause();
    }
}