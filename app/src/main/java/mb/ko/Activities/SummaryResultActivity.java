package mb.ko.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import mb.ko.R;

public class SummaryResultActivity extends AppCompatActivity {

    private TextView tvSummaryStage, tvSummaryCompetitorsAmount, tvSummaryTime, tvSummaryPoints, tvSummaryNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_result);

        tvSummaryStage = (TextView)findViewById(R.id.tvSummaryStage);
        tvSummaryCompetitorsAmount = (TextView)findViewById(R.id.tvSummaryCompetitorsAmount);
        tvSummaryTime = (TextView)findViewById(R.id.tvSummaryTime);
        tvSummaryPoints = (TextView)findViewById(R.id.tvSummaryPoints);
        tvSummaryNumber = (TextView)findViewById(R.id.tvSummaryNumber);

        tvSummaryStage.setText(String.valueOf(getIntent().getIntExtra("stage", 0)));
        tvSummaryNumber.setText(String.valueOf(getIntent().getIntExtra("number", 0)));
        tvSummaryTime.setText(getIntent().getStringExtra("time"));
        tvSummaryPoints.setText(String.valueOf(getIntent().getIntExtra("points", 0)));

    }
}
