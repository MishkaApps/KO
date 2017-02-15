package mb.ko.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;

import mb.ko.Activities.TimerActivity;
import mb.ko.Activities.WorkActivity;
import mb.ko.R;
import mb.ko.Time;
import mb.ko.Timer;
import mb.ko.WorkFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultPointsTimerFragment extends Fragment implements View.OnClickListener, WorkFragment, View.OnKeyListener {
    private EditText etResult, etPoints;
    private Button btnStartStop, btnReset;
    private Timer timer;
    private WorkActivity workActivity;


    public ResultPointsTimerFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_points_timer, container, false);
        etPoints = (EditText) view.findViewById(R.id.etPoints);
        etResult = (EditText) view.findViewById(R.id.etResult);
        btnStartStop = (Button) view.findViewById(R.id.btnStartStop);
        btnReset = (Button) view.findViewById(R.id.btnReset);
        timer = (Timer) view.findViewById(R.id.timer);
        timer.setTimerParameters(getResources().getInteger(R.integer.timer_duration), getResources().getInteger(R.integer.timer_tick_period));
        timer.setStartStopButton(btnStartStop);

        btnStartStop.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        etPoints.setOnKeyListener(this);
        timer.setOnClickListener(this);
        etPoints.setText("");
        etResult.setText("");

        return view;
    }

    @Override
    public void onClick(View v) {

        if (v == btnStartStop) {
            if (!timer.isRun())
                timer.start();
            else
                timer.stop();
            return;
        }

        if (v == btnReset) {
            timer.reset();
            return;
        }

        if (v == timer){
            Intent intent = new Intent(getActivity(), TimerActivity.class);
            startActivityForResult(intent, getResources().getInteger(R.integer.set_timer_time));
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        timer.setTimerParameters(data.getLongExtra(getResources().getString(R.string.timer_time), 1000), getResources().getInteger(R.integer.timer_tick_period));
    }

    @Override
    public Time getTime() {
        return null;
    }

    @Override
    public int getPoints() {
        return 0;
    }

    @Override
    public void setWorkActivity(WorkActivity workActivity) {
        this.workActivity = workActivity;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (v == etPoints)
            if (etPoints.getText().length() > 0)
                workActivity.pointsFieldUsed(true);
            else
                workActivity.pointsFieldUsed(false);

        if (v == etResult)
            if (etResult.getText().length() > 0)
                workActivity.resultFieldUsed(true);
            else
                workActivity.resultFieldUsed(false);

        return false;
    }
}
