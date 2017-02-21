package mb.ko.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mb.ko.R;
import mb.ko.Stage;
import mb.ko.WorkActivityType;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnTimePlusPoints, btnTime, btnResultPlusPoints, btnResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btnTimePlusPoints = (Button)findViewById(R.id.btnTimePlusPoints);
        btnTimePlusPoints.setOnClickListener(this);

        btnTime = (Button)findViewById(R.id.btnTime);
        btnTime.setOnClickListener(this);

        btnResultPlusPoints = (Button)findViewById(R.id.btnResultPlusPoints);
        btnResultPlusPoints.setOnClickListener(this);

        btnResult = (Button)findViewById(R.id.btnResultPlusTimer);
        btnResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, StageNumberActivity.class);
        switch(v.getId()){
            case R.id.btnTimePlusPoints:
                intent.putExtra(getResources().getString(R.string.StageAsExtra), new Stage(WorkActivityType.StopwatchAndPoints));
                break;
            case R.id.btnTime:
                intent.putExtra(getResources().getString(R.string.StageAsExtra), new Stage(WorkActivityType.Stopwatch));
                break;
            case R.id.btnResultPlusPoints:
                intent.putExtra(getResources().getString(R.string.StageAsExtra), new Stage(WorkActivityType.ResultPointsAndTimer));
                break;
            case R.id.btnResultPlusTimer:
                intent.putExtra(getResources().getString(R.string.StageAsExtra), new Stage(WorkActivityType.ResultAndTimer));
                break;


        }

        startActivity(intent);
    }
}
