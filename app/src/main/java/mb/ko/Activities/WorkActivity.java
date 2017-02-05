package mb.ko.Activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import mb.ko.Fragments.ResultPointsTimerFragment;
import mb.ko.Fragments.ResultTimerFragment;
import mb.ko.Fragments.StopwatchFragment;
import mb.ko.R;
import mb.ko.Fragments.StopwatchPointsFragment;
import mb.ko.Stage;
import mb.ko.WorkFragment;

public class WorkActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnFinish;
    private TextView tvStage, tvCompetitorNumber, tvSummaryCompetitorsAmount;
    private Stage stage;
    private WorkFragment workFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);


        btnFinish = (Button)findViewById(R.id.btnFinish);
        tvStage = (TextView)findViewById(R.id.tvStage);
        tvCompetitorNumber = (TextView)findViewById(R.id.tvCompetitorNumber);
        tvSummaryCompetitorsAmount = (TextView)findViewById(R.id.tvSummaryCompetitorsAmount);

        stage = (Stage) getIntent().getSerializableExtra(getResources().getString(R.string.StageAsExtra));

        tvStage.setText(String.valueOf(stage.getNumber()));
        tvCompetitorNumber.setText(String.valueOf(stage.getCompetitorNumber()));
        tvSummaryCompetitorsAmount.setText(String.valueOf(stage.getSummaryCompetitorsAmount()));

        // устанавливает необходимый фрагмент в зависимости от типа выбраного этапа
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        switch (stage.getType()){
            case StopwatchAndPoints:
                workFragment = new StopwatchPointsFragment();
                fragmentTransaction.add(R.id.lytFragmentContainer, (Fragment) workFragment);
                break;
            case Stopwatch:
                workFragment = new StopwatchFragment();
                fragmentTransaction.add(R.id.lytFragmentContainer, (Fragment) workFragment);
                break;
            case ResultPointsAndTimer:
                workFragment = new ResultPointsTimerFragment();
                fragmentTransaction.add(R.id.lytFragmentContainer, (Fragment) workFragment);
                break;
            case ResultAndTimer:
                workFragment = new ResultTimerFragment();
                fragmentTransaction.add(R.id.lytFragmentContainer, (Fragment) workFragment);
                break;
        }
        fragmentTransaction.commit();

        btnFinish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        if(v == btnFinish){
            Intent intent = new Intent(this, SummaryResultActivity.class);
            stage.getCurrentCompetitor().setPoints(workFragment.getPoints());
            workFragment.getTime();

            intent.putExtra(getResources().getString(R.string.StageAsExtra), stage);
            startActivity(intent);
        }
    }
}
