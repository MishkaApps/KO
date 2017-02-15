package mb.ko.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import mb.ko.R;
import mb.ko.Stage;

public class CompetitorNumberActivity extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener {

    private EditText etNumber;
    private Button btnNext;
    private Stage previousStage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competitor_number);

        etNumber = (EditText) findViewById(R.id.tvCompetitorNumber);
        btnNext = (Button) findViewById(R.id.btnNext);

        etNumber.setOnKeyListener(this);
        btnNext.setOnClickListener(this);

        etNumber.setText("");
        etNumber.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);  // todo Узнать как точно работает этот метод
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if (v == etNumber && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP)
            if (etNumber.getText().length() > 0)
                next();

        return false;
    }

    @Override
    public void onClick(View v) {
        if (v == btnNext)
            if (etNumber.getText().length() > 0)
                next();

    }

    private void next() {
        Intent intent = new Intent(this, WorkActivity.class);

        Stage stage = (Stage) getIntent().getSerializableExtra(getResources().getString(R.string.StageAsExtra));

        stage.setCompetitor(Integer.decode(etNumber.getText().toString()));
        intent.putExtra(getResources().getString(R.string.StageAsExtra), stage);

        startActivity(intent);
    }
}
