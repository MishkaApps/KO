package mb.ko.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;

import mb.ko.Activities.WorkActivity;
import mb.ko.R;
import mb.ko.Time;
import mb.ko.WorkFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class StopwatchPointsFragment extends Fragment implements WorkFragment, View.OnClickListener, View.OnKeyListener {
    private Chronometer chronometer;
    private Time chronometerTime;
    private EditText etPoints;
    private Button btnStartStop, btnReset;

    private boolean boolChronometerRun;
    private WorkActivity workActivity;

    public StopwatchPointsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stopwatch_points, container, false);
        chronometer = (Chronometer) view.findViewById(R.id.stopwatch);
        etPoints = (EditText) view.findViewById(R.id.etPoints);
        btnStartStop = (Button) view.findViewById(R.id.btnStartStop);
        btnReset = (Button) view.findViewById(R.id.btn_reset);

        btnStartStop.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        etPoints.setOnKeyListener(this);
        boolChronometerRun = false;
        etPoints.setText("");

        chronometerTime = new Time(0);

        return view;
    }

    @Override
    public Time getTime() {
        return chronometerTime;
    }

    @Override
    public int getPoints() {
        return Integer.parseInt(etPoints.getText().toString());
    }

    @Override
    public int getResult() {
        return 0;
    }

    @Override
    public void setWorkActivity(WorkActivity workActivity) {
        this.workActivity = workActivity;
    }

    @Override
    public void setTimerDuration(long timerDuration) {

    }

    @Override
    public void onClick(View v) {
        if (v == btnStartStop) {
            if (!boolChronometerRun) {
                chronometer.setBase(SystemClock.elapsedRealtime() - chronometerTime.getTime());
                chronometer.start();
                btnStartStop.setText(getResources().getString(R.string.line_STOP));
                boolChronometerRun = true;
            } else {
                chronometer.stop();
                chronometerTime = new Time(SystemClock.elapsedRealtime() - chronometer.getBase());
                btnStartStop.setText(getResources().getString(R.string.line_START));
                boolChronometerRun = false;
                workActivity.chronometerUsed(true);
            }
        } else if(v == btnReset){
            chronometer.stop();
            chronometerTime = new Time(0);
            btnStartStop.setText(getResources().getString(R.string.line_START));
            chronometer.setBase(SystemClock.elapsedRealtime());
            boolChronometerRun = false;
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(etPoints.getText().length() > 0)
            workActivity.pointsFieldUsed(true);
        else
            workActivity.pointsFieldUsed(false);
        return false;
    }
}
