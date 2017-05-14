package mb.ko.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mb.ko.R;
import mb.ko.Stage;

public class CompetitorNumberActivity extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener {

    private EditText etNumber;
    private Button btnNext;
    private Stage previousStage;
    private static char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

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
                next();

        return false;
    }

    @Override
    public void onClick(View v) {
        if (v == btnNext)
                next();

    }

    private void next() {

        if (!isNumberValid(etNumber.getText().toString())){
            Toast.makeText(this, "Введите корректный номер", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, WorkActivity.class);

        Stage stage = (Stage) getIntent().getSerializableExtra(getResources().getString(R.string.StageAsExtra));
        stage.setCurrentCompetitorNumber(Integer.decode(etNumber.getText().toString()));
        intent.putExtra(getResources().getString(R.string.StageAsExtra), stage);

        startActivity(intent);
    }

    public static boolean isNumberValid(String number){
        char[] chars = number.toCharArray();

        if(chars.length == 0)
            return false;

        if(chars[0] == '0')
            return false;

        boolean valid = true;
        boolean isDigit;
        for(int charCounter = 0; charCounter < chars.length; ++charCounter){
            isDigit = false;
            for(int digitCounter = 0; digitCounter < digits.length; ++digitCounter){
                if(chars[charCounter] == digits[digitCounter]) {
                    isDigit = true;
                    break;
                }
            }
            valid &= isDigit;
        }

        return valid;
    }
}
