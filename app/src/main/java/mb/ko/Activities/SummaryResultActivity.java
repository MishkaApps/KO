package mb.ko.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import mb.ko.R;
import mb.ko.Stage;

public class SummaryResultActivity extends AppCompatActivity {

    private TextView tvStage, tvSummaryCompetitorsAmount, tvTime, tvPoints, tvCompetitorNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_result);

        tvStage = (TextView)findViewById(R.id.tvStage);
        tvCompetitorNumber = (TextView)findViewById(R.id.tvCompetitorNumber);
        tvSummaryCompetitorsAmount = (TextView)findViewById(R.id.tvSummaryCompetitorsAmount);
        tvPoints = (TextView)findViewById(R.id.tvPoints);
        tvTime = (TextView)findViewById(R.id.tvTime);

        Stage stage = (Stage) getIntent().getSerializableExtra(getResources().getString(R.string.StageAsExtra));

        tvStage.setText(String.valueOf(stage.getNumber()));
        tvCompetitorNumber.setText(String.valueOf(stage.getCompetitorNumber()));
        tvSummaryCompetitorsAmount.setText(String.valueOf(stage.getSummaryCompetitorsAmount()));
        tvPoints.setText(String.valueOf(stage.getCurrentCompetitor().getPoints()));

    }
}
