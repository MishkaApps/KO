package mb.ko.Activities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

import mb.ko.Fragments.ResultPointsTimerFragment;
import mb.ko.Fragments.ResultTimerFragment;
import mb.ko.Fragments.StopwatchFragment;
import mb.ko.R;
import mb.ko.Fragments.StopwatchPointsFragment;
import mb.ko.Stage;

public class WorkActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnStartStop, btnFinish;
    private Chronometer stopwatch;
    private Boolean stopwatchRun;
    private TextView tvStage, tvNumber;
    private EditText etPoints;
    private Stage stage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        stopwatchRun = false;

//        btnStartStop = (Button)findViewById(R.id.button_start_stop_time);
        btnFinish = (Button)findViewById(R.id.btnFinish);
        stopwatch = (Chronometer)findViewById(R.id.stopwatch);
        tvStage = (TextView)findViewById(R.id.etStage);
        tvNumber = (TextView)findViewById(R.id.etNumber);
        etPoints = (EditText)findViewById(R.id.etPoints);

        tvStage.setText(String.valueOf(getIntent().getIntExtra("stage", 0)));
        tvNumber.setText(String.valueOf(getIntent().getIntExtra("number", 0)));

        stage = (Stage) getIntent().getSerializableExtra(getResources().getString(R.string.StageAsExtra));

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        switch (stage.getType()){
            case StopwatchAndPoints:
                fragmentTransaction.add(R.id.lytFragmentContainer, new StopwatchPointsFragment());
                break;
            case Stopwatch:
                fragmentTransaction.add(R.id.lytFragmentContainer, new StopwatchFragment());
                break;
            case ResultPointsAndTimer:
                fragmentTransaction.add(R.id.lytFragmentContainer, new ResultPointsTimerFragment());
                break;
            case ResultAndTimer:
                fragmentTransaction.add(R.id.lytFragmentContainer, new ResultTimerFragment());
                break;
        }
        fragmentTransaction.commit();

//        btnStartStop.setOnClickListener(this);
        btnFinish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == btnStartStop){
            if(!stopwatchRun) {
                stopwatch.setBase(SystemClock.elapsedRealtime());
                stopwatch.start();
                stopwatchRun = true;
                btnStartStop.setText("Стоп");
            } else {
                stopwatch.stop();
                stopwatchRun = false;
                btnStartStop.setText("Старт");
            }
        } else if(v == btnFinish){
            Intent intent = new Intent(this, SummaryResultActivity.class);

            intent.putExtra("stage", getIntent().getIntExtra("stage", 0));
            intent.putExtra("number", getIntent().getIntExtra("number", 0));
//            intent.putExtra("time", stopwatch.getText().toString());
//            intent.putExtra("points", Integer.decode(etPoints.getText().toString()));

            startActivity(intent);
        }
    }
}
