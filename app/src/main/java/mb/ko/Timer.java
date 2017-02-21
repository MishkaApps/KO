package mb.ko;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by mbolg on 14.02.2017.
 */
public class Timer extends TextView {
    private CountDownTimer timer;
    private long duration, tickPeriod;
    private boolean run;
    private Time time;
    private long currentTime;
    private Button btnStartStop;

    public Timer(Context context, AttributeSet attrs) {
        super(context, attrs);
        time = new Time();
        currentTime = 0;

    }

    public CountDownTimer createTimer(long duration) {
        setText(time.formatTime(duration));
        return new CountDownTimer(duration, tickPeriod) {
            @Override
            public void onTick(long millisUntilFinished) {
                currentTime = millisUntilFinished;
                if (millisUntilFinished % 1000 < 3)
                    setText(time.formatTime(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                setText("00:00");
                currentTime = 0;
                btnStartStop.setText(getResources().getString(R.string.line_START));
                run = false;
            }
        };
    }

    public void setTimerParameters(long duration, long tickPeriod) {
        setText(time.formatTime(duration));
        this.duration = duration;
        this.tickPeriod = tickPeriod;

    }

    public void setStartStopButton(Button btnStartStop) {
        this.btnStartStop = btnStartStop;
    }

    public void start() {
        if (currentTime == 0) {
            timer = createTimer(duration);
            timer.start();
            btnStartStop.setText(getResources().getString(R.string.line_STOP));
        } else {
            timer = createTimer(currentTime);
            timer.start();
            btnStartStop.setText(getResources().getString(R.string.line_STOP));
        }
        run = true;
    }

    public void stop() {
        timer.cancel();
        btnStartStop.setText(getResources().getString(R.string.line_START));
        run = false;
    }

    public void reset() {
        if (timer != null)
            stop();
        setText(time.formatTime(duration));
        currentTime = 0;
    }

    public boolean isRun() {
        return run;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
