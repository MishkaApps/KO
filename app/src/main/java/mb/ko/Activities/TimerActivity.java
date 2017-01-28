package mb.ko.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mb.ko.R;

public class TimerActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        btnNext = (Button)findViewById(R.id.btnFinish);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}
