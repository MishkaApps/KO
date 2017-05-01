package mb.ko.Activities;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import mb.ko.R;
import mb.ko.ResultWriter;
import mb.ko.Stage;
import mb.ko.WorkActivityType;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnTimePlusPoints, btnTime, btnResultPlusPoints, btnResult, btnPass, btnMooseRaces;

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

        btnPass = (Button)findViewById(R.id.btn_pass);
        btnPass.setOnClickListener(this);

        btnMooseRaces = (Button)findViewById(R.id.btn_moose_races);
        btnMooseRaces.setOnClickListener(this);

        ResultWriter.createResultsFile();
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
            case R.id.btn_pass:
                intent.putExtra(getResources().getString(R.string.StageAsExtra), new Stage(WorkActivityType.Pass));
                break;
            case R.id.btn_moose_races:
                intent.putExtra(getResources().getString(R.string.StageAsExtra), new Stage(WorkActivityType.MooseRaces));
                break;


        }

        startActivity(intent);
    }
}
