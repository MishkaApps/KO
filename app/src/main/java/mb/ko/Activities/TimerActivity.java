package mb.ko.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import mb.ko.R;

public class TimerActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnOk;
    private EditText etMinutes, etSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        btnOk = (Button)findViewById(R.id.btnFinish);
        btnOk.setOnClickListener(this);

        etMinutes = (EditText)findViewById(R.id.etMinutes);
        etSeconds = (EditText)findViewById(R.id.etSeconds);
    }

    @Override
    public void onClick(View v) {
        if(v == btnOk){
            Intent intent = new Intent();
            long time = Integer.decode(etMinutes.getText().toString()) * 60000 + Integer.decode(etSeconds.getText().toString())*1000;
            intent.putExtra(getResources().getString(R.string.timer_time), time);
            setResult(getResources().getInteger(R.integer.set_timer_time), intent);
            finish();

        }
    }
}
