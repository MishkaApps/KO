package mb.ko.Activities;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import mb.ko.Fragments.ResultPointsTimerFragment;
import mb.ko.Fragments.ResultTimerFragment;
import mb.ko.Fragments.StopwatchFragment;
import mb.ko.Fragments.StopwatchPointsFragment;
import mb.ko.R;
import mb.ko.Stage;

public class SummaryResultActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvStage, tvSummaryCompetitorsAmount, tvTime, tvPoints, tvCompetitorNumber;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_result);

        tvStage = (TextView) findViewById(R.id.tvStage);
        tvCompetitorNumber = (TextView) findViewById(R.id.tvCompetitorNumber);
        tvSummaryCompetitorsAmount = (TextView) findViewById(R.id.tvSummaryCompetitorsAmount);
        tvPoints = (TextView) findViewById(R.id.tvPoints);
        tvTime = (TextView) findViewById(R.id.tvTime);

        Stage stage = (Stage) getIntent().getSerializableExtra(getResources().getString(R.string.StageAsExtra));

        tvStage.setText(String.valueOf(stage.getNumber()));
        tvCompetitorNumber.setText(String.valueOf(stage.getCompetitorNumber()));
        tvSummaryCompetitorsAmount.setText(String.valueOf(stage.getSummaryCompetitorsAmount()));


        switch (stage.getType()) {
            case StopwatchAndPoints:
                tvPoints.setText(String.valueOf(stage.getCurrentCompetitor().getPoints()));
                tvTime.setText((stage.getCurrentCompetitor().getTime()));
                break;
            case Stopwatch:
                findViewById(R.id.lytSummaryResultPoints).setVisibility(View.GONE);
                tvTime.setText((stage.getCurrentCompetitor().getTime()));
                break;
            case ResultPointsAndTimer:
                break;
            case ResultAndTimer:
                break;
        }

        tvPoints.setText(String.valueOf(stage.getCurrentCompetitor().getPoints()));
        tvTime.setText((stage.getCurrentCompetitor().getTime()));

        btnNext = (Button) findViewById(R.id.btnSummaryResultNext);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, CompetitorNumberActivity.class);

        Stage stage = (Stage) getIntent().getSerializableExtra(getResources().getString(R.string.StageAsExtra));
        intent.putExtra(getResources().getString(R.string.StageAsExtra), stage);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
    }
}
