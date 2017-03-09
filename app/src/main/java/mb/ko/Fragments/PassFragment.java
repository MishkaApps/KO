package mb.ko.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import mb.ko.Activities.TimerActivity;
import mb.ko.Activities.WorkActivity;
import mb.ko.R;
import mb.ko.Time;
import mb.ko.Timer;
import mb.ko.WorkFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PassFragment extends Fragment implements View.OnClickListener, WorkFragment {
    private Button btnStartStop, btnReset;
    private Timer timer;
    private RadioButton rbtnPass, rbtnNotPass;
    private WorkActivity workActivity;


    public PassFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pass, container, false);
        btnStartStop = (Button) view.findViewById(R.id.btnStartStop);
        btnReset = (Button) view.findViewById(R.id.btnReset);
        timer = (Timer) view.findViewById(R.id.timer);
        timer.setStartStopButton(btnStartStop);

        rbtnPass = (RadioButton) view.findViewById(R.id.rbtn_pass);
        rbtnNotPass = (RadioButton) view.findViewById(R.id.rbtn_not_pass);

        rbtnPass.setOnClickListener(this);
        rbtnNotPass.setOnClickListener(this);

        rbtnPass.setChecked(false);
        rbtnNotPass.setChecked(false);

        btnStartStop.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        timer.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        if (v == btnStartStop) {
            if (!timer.isRun())
                timer.start();
            else {
                timer.stop();
                workActivity.chronometerUsed(true);
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

        if(v == rbtnPass || v == rbtnNotPass)
            workActivity.radioGroupUsed(true);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        workActivity.saveTimerDuration(data.getLongExtra(getResources().getString(R.string.timer_time), 1000));
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
    public boolean getPass() {
        return rbtnPass.isChecked();
    }

    @Override
    public void setWorkActivity(WorkActivity workActivity) {
        this.workActivity = workActivity;
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
