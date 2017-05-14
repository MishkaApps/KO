package mb.ko.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import mb.ko.R;
import mb.ko.Stage;
import mb.ko.WorkActivityType;

public class StageNumberActivity extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener {
    private EditText etStage;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_number);

        etStage = (EditText) findViewById(R.id.tvStage);
        btnNext = (Button) findViewById(R.id.btnStageNumberNext);

        etStage.setOnKeyListener(this);
        btnNext.setOnClickListener(this);

        etStage.setText("");
        etStage.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);  // todo Узнать как точно работает этот метод
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if (v == etStage && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP)
            if (etStage.getText().length() > 0)
                next();

        return false;
    }

    @Override
    public void onClick(View v) {
        if (v == btnNext)
            if (etStage.getText().length() > 0)
                next();

    }

    private void next() {


        Stage stage = (Stage) getIntent().getSerializableExtra(getResources().getString(R.string.StageAsExtra));

        if (stage.getType() == WorkActivityType.ResultPointsAndTimer
                || stage.getType() == WorkActivityType.ResultAndTimer
                || stage.getType() == WorkActivityType.Pass) {

            Intent intent = new Intent(this, TimerActivity.class);

            Integer stageNumber = Integer.decode(etStage.getText().toString());
            stage.setNumber(stageNumber);
            intent.putExtra(getResources().getString(R.string.StageAsExtra), stage);
            intent.putExtra(getResources().getString(R.string.timer_time), getResources().getString(R.string.without_result));

            startActivity(intent);
        } else if (stage.getType() == WorkActivityType.MooseRaces) {

            Intent intent;
            intent = new Intent(this, WorkActivity.class);

            Integer stageNumber = Integer.decode(etStage.getText().toString());
            stage.setNumber(stageNumber);
            long time = 60000L;
            stage.setTimerDuration(time);
            intent.putExtra(getResources().getString(R.string.StageAsExtra), stage);

            startActivity(intent);
        } else {

            Intent intent = new Intent(this, CompetitorNumberActivity.class);

            Integer stageNumber = Integer.decode(etStage.getText().toString());
            stage.setNumber(stageNumber);
            intent.putExtra(getResources().getString(R.string.StageAsExtra), stage);

            startActivity(intent);
        }
    }
}
