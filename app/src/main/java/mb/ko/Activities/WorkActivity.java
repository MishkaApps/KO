package mb.ko.Activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import mb.ko.Fragments.MooseRacesFragment;
import mb.ko.Fragments.PassFragment;
import mb.ko.Fragments.ResultPointsTimerFragment;
import mb.ko.Fragments.ResultTimerFragment;
import mb.ko.Fragments.StopwatchFragment;
import mb.ko.R;
import mb.ko.Fragments.StopwatchPointsFragment;
import mb.ko.Stage;
import mb.ko.WorkActivityType;
import mb.ko.WorkFragment;

public class WorkActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnFinish;
    private TextView tvStage, tvCompetitorNumber, tvSummaryCompetitorsAmount;
    private Stage stage;
    private WorkFragment workFragment;
    private boolean chronometerUsed, pointsFieldUsed, resultFieldUsed, radioGroupUsed;
    private CheckBox cbxSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);


        btnFinish = (Button) findViewById(R.id.btnFinish);
        tvStage = (TextView) findViewById(R.id.tvStage);
        tvCompetitorNumber = (TextView) findViewById(R.id.tvCompetitorNumber);
        tvSummaryCompetitorsAmount = (TextView) findViewById(R.id.tvSummaryCompetitorsAmount);
        cbxSuccess = (CheckBox) findViewById(R.id.cbxSuccess);

        stage = (Stage) getIntent().getSerializableExtra(getResources().getString(R.string.StageAsExtra));

        tvStage.setText(String.valueOf(stage.getNumber()));
        if (stage.getType() != WorkActivityType.MooseRaces)
            tvCompetitorNumber.setText(String.valueOf(stage.getCompetitorNumber()));
        tvSummaryCompetitorsAmount.setText(String.valueOf(stage.getSummaryCompetitorsAmount()));


        // устанавливает необходимый фрагмент в зависимости от типа выбраного этапа
        // поля chronometerUsed и pointsFieldUsed используются для индикации момента, когда сукундомер будет использован и баллы введены.
        // Тогда кнопка "Далее" будет доступна
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        switch (stage.getType()) {
            case StopwatchAndPoints:
                workFragment = new StopwatchPointsFragment();
                fragmentTransaction.add(R.id.lytFragmentContainer, (Fragment) workFragment);
                chronometerUsed = false;
                pointsFieldUsed = false;
                resultFieldUsed = true;
                radioGroupUsed = true;
                break;
            case Stopwatch:
                workFragment = new StopwatchFragment();
                fragmentTransaction.add(R.id.lytFragmentContainer, (Fragment) workFragment);
                chronometerUsed = false;
                pointsFieldUsed = true;
                resultFieldUsed = true;
                radioGroupUsed = true;
                break;
            case ResultPointsAndTimer:
                workFragment = new ResultPointsTimerFragment();
                fragmentTransaction.add(R.id.lytFragmentContainer, (Fragment) workFragment);
                chronometerUsed = false;
                pointsFieldUsed = false;
                resultFieldUsed = false;
                radioGroupUsed = true;
                break;
            case ResultAndTimer:
                workFragment = new ResultTimerFragment();
                fragmentTransaction.add(R.id.lytFragmentContainer, (Fragment) workFragment);
                chronometerUsed = false;
                pointsFieldUsed = true;
                resultFieldUsed = false;
                radioGroupUsed = true;
                break;
            case Pass:
                workFragment = new PassFragment();
                fragmentTransaction.add(R.id.lytFragmentContainer, (Fragment) workFragment);
                chronometerUsed = false;
                pointsFieldUsed = true;
                resultFieldUsed = true;
                radioGroupUsed = false;
                cbxSuccess.setVisibility(View.GONE);
                break;
            case MooseRaces:
                workFragment = new MooseRacesFragment();
                fragmentTransaction.add(R.id.lytFragmentContainer, (Fragment) workFragment);
                chronometerUsed = true;
                pointsFieldUsed = true;
                resultFieldUsed = true;
                radioGroupUsed = true;
                findViewById(R.id.lyt_cbx_success).setVisibility(View.GONE);
                btnFinish.setVisibility(View.GONE);
                findViewById(R.id.lyt_competitor_number).setVisibility(View.GONE);
                break;
        }
        fragmentTransaction.commit();

        workFragment.setWorkActivity(this);

        btnFinish.setOnClickListener(this);

        btnFinish.setEnabled(chronometerUsed && pointsFieldUsed && resultFieldUsed);
    }

    @Override
    protected void onResume() {
        super.onResume();
        workFragment.setTimerDuration(stage.getTimerDuration());
    }

    @Override
    public void onClick(View v) {
        if (v == btnFinish) {
            Intent intent = new Intent(this, SummaryResultActivity.class);
            stage.getCurrentCompetitor().setPoints(workFragment.getPoints());
            stage.getCurrentCompetitor().setTime(workFragment.getTime());
            stage.getCurrentCompetitor().setResult(workFragment.getResult());
            stage.getCurrentCompetitor().setPass(workFragment.getPass());
            if (stage.getType() != WorkActivityType.Pass)
                stage.getCurrentCompetitor().setSuccess(cbxSuccess.isChecked());
            else
                stage.getCurrentCompetitor().setSuccess(workFragment.getPass());

            intent.putExtra(getResources().getString(R.string.StageAsExtra), stage);
            startActivity(intent);
        }
    }

    public void chronometerUsed(boolean flag) {
        chronometerUsed = flag;
        btnFinish.setEnabled(chronometerUsed && pointsFieldUsed && resultFieldUsed && radioGroupUsed);
    }

    public void pointsFieldUsed(boolean flag) {
        pointsFieldUsed = flag;
        btnFinish.setEnabled(chronometerUsed && pointsFieldUsed && resultFieldUsed && radioGroupUsed);
    }

    public void resultFieldUsed(boolean flag) {
        resultFieldUsed = flag;
        btnFinish.setEnabled(chronometerUsed && pointsFieldUsed && resultFieldUsed && radioGroupUsed);
    }

    public void radioGroupUsed(boolean flag) {
        radioGroupUsed = flag;
        btnFinish.setEnabled(chronometerUsed && pointsFieldUsed && resultFieldUsed && radioGroupUsed);
    }


    public void saveTimerDuration(long time) {
        stage.setTimerDuration(time);
        workFragment.setTimerDuration(stage.getTimerDuration());
    }


}
