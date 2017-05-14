package mb.ko.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import mb.ko.R;
import mb.ko.Stage;
import mb.ko.WorkActivityType;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnTimePlusPoints, btnTime, btnResultPlusPoints, btnResult, btnPass, btnMooseRaces;
    private Button btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btnTimePlusPoints = (Button) findViewById(R.id.btnTimePlusPoints);
        btnTimePlusPoints.setOnClickListener(this);

        btnTime = (Button) findViewById(R.id.btnTime);
        btnTime.setOnClickListener(this);

        btnResultPlusPoints = (Button) findViewById(R.id.btnResultPlusPoints);
        btnResultPlusPoints.setOnClickListener(this);

        btnResult = (Button) findViewById(R.id.btnResultPlusTimer);
        btnResult.setOnClickListener(this);

        btnPass = (Button) findViewById(R.id.btn_pass);
        btnPass.setOnClickListener(this);

        btnMooseRaces = (Button) findViewById(R.id.btn_moose_races);
        btnMooseRaces.setOnClickListener(this);

        btnSettings = (Button) findViewById(R.id.btn_settings);
        btnSettings.setOnClickListener(this);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }


    @Override
    public void onClick(View v) {

        if (v == btnSettings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivityForResult(intent, 0);
            return;
        }

        Intent intent = new Intent(this, StageNumberActivity.class);

        switch (v.getId()) {
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
