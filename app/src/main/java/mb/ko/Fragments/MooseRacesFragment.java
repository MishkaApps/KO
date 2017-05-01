package mb.ko.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mb.ko.Activities.CompetitorNumberActivity;
import mb.ko.Activities.TimerActivity;
import mb.ko.Activities.WorkActivity;
import mb.ko.R;
import mb.ko.Time;
import mb.ko.Timer;
import mb.ko.WorkFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MooseRacesFragment extends Fragment implements WorkFragment, View.OnClickListener {
    private EditText etTeamNumber;
    private WorkActivity workActivity;
    private Button btnStartStop, btnReset;
    private Button btnSaveTime;
    private Timer timer;


    public MooseRacesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_moose_races, container, false);
        etTeamNumber = (EditText) view.findViewById(R.id.et_team_number);
        btnStartStop = (Button) view.findViewById(R.id.btnStartStop);
        btnReset = (Button) view.findViewById(R.id.btnReset);
        btnSaveTime = (Button) view.findViewById(R.id.btnSaveTime);
        timer = (Timer) view.findViewById(R.id.timer);
        timer.setStartStopButton(btnStartStop);

        btnStartStop.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnSaveTime.setOnClickListener(this);
        timer.setOnClickListener(this);
        etTeamNumber.setText("");

        timer.cycle();

        return view;
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
    public int getResult() {
        return 0;
    }

    @Override
    public void setWorkActivity(WorkActivity workActivity) {
        this.workActivity = workActivity;
    }


    @Override
    public void onClick(View v) {

        if (v == btnStartStop) {
            if (!timer.isRun())
                timer.start();
            else {
                timer.stop();
            }
            return;
        }

        if (v == btnReset) {
            timer.reset();
            return;
        }

        if (v == timer) {
            timer.reset();
            Intent intent = new Intent(getActivity(), TimerActivity.class);
            intent.putExtra(getResources().getString(R.string.timer_time), getResources().getString(R.string.with_result));
            startActivityForResult(intent, getResources().getInteger(R.integer.set_timer_time));
        }

        if(v == btnSaveTime){
            if (!CompetitorNumberActivity.isNumberValid(etTeamNumber.getText().toString())){
                Toast.makeText(workActivity, "Введите корректный номер команды", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(workActivity, "Время команды №" + etTeamNumber.getText().toString() + " записано", Toast.LENGTH_SHORT).show();
            etTeamNumber.setText("");
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        workActivity.saveTimerDuration(data.getLongExtra(getResources().getString(R.string.timer_time), 1000));
    }


    @Override
    public void setTimerDuration(long timerDuration) {
        timer.setTimerParameters(timerDuration, workActivity.getResources().getInteger(R.integer.timer_tick_period));
        boolean flag = timerDuration == 0L;
        workActivity.chronometerUsed(flag);
        btnStartStop.setEnabled(!flag);
        btnReset.setEnabled(!flag);
    }

}
