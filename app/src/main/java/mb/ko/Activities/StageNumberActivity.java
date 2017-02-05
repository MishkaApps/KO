package mb.ko.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import mb.ko.R;
import mb.ko.Stage;

public class StageNumberActivity extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener {
    private EditText etStage;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_number);

        etStage = (EditText)findViewById(R.id.tvStage);
        btnNext = (Button)findViewById(R.id.btnStageNumberNext);

        etStage.setOnKeyListener(this);
        btnNext.setOnClickListener(this);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if(v == etStage && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP)
            next();

        return false;
    }

    @Override
    public void onClick(View v) {
        if(v == btnNext)
            next();

    }

    private void next(){
        Intent intent = new Intent(this, CompetitorNumberActivity.class);

        Integer stageNumber = Integer.decode(etStage.getText().toString());
        Stage stage = (Stage) getIntent().getSerializableExtra(getResources().getString(R.string.StageAsExtra));
        stage.setNumber(stageNumber);
        intent.putExtra(getResources().getString(R.string.StageAsExtra), stage);

        startActivity(intent);
    }
}
