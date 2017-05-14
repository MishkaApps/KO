package mb.ko.Activities;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import mb.ko.Fragments.DatePickerFragment;
import mb.ko.R;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private EditText name, surname, phone;
    private TextView date;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
        }


        name = (EditText) findViewById(R.id.et_name);
        surname = (EditText) findViewById(R.id.et_surname);
        date = (TextView) findViewById(R.id.tv_date);
        phone = (EditText) findViewById(R.id.et_phone);

        save = (Button) findViewById(R.id.btn_save);
        save.setOnClickListener(this);

        date.setOnClickListener(this);
        date.setText("2000.01.01");

        restoreData();
    }

    private void restoreData(){
        SharedPreferences sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        name.setText(sharedPreferences.getString("name", ""));
        surname.setText(sharedPreferences.getString("surname", ""));
        date.setText(sharedPreferences.getString("birthday", "2000.01.01"));
        phone.setText(sharedPreferences.getString("phone", "+7-"));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0) {
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
            SharedPreferences sharedPreferences = getSharedPreferences("preferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("id", telephonyManager.getDeviceId());
            editor.apply();
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onClick(View v) {
        if (v == save) {
            if (!validate())
                return;
            TelephonyManager telephonyManager = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
            SharedPreferences sharedPreferences = getSharedPreferences("preferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", name.getText().toString());
            editor.putString("surname", surname.getText().toString());
            editor.putString("birthday", date.getText().toString());
            editor.putString("phone", phone.getText().toString());
            editor.putString("id", telephonyManager.getDeviceId());
            editor.apply();
            Toast.makeText(this, "Сохранено", Toast.LENGTH_SHORT).show();
            finish();
        }
        if (v == date) {
            DatePickerFragment dialogFragment = new DatePickerFragment();
            String currentDate = date.getText().toString();
            int year = Integer.parseInt(currentDate.substring(0, 4));
            int month = Integer.parseInt(currentDate.substring(5, 7)) - 1;
            int day = Integer.parseInt(currentDate.substring(8));
            dialogFragment.setDate(year, month, day);
            dialogFragment.show(getFragmentManager(), "date picker");
        }
    }

    private boolean validate() {
        if (name.getText().toString().length() < 1) {
            Toast.makeText(this, "Ошибка при вводе имени", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (surname.getText().toString().length() < 1) {
            Toast.makeText(this, "Ошибка при вводе фамилии", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (phone.getText().toString().length() != 16) {
            Toast.makeText(this, "Ошибка при вводе телефона", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        ++month;
        String formatedMonth = (month < 10) ? ("0" + month):("" + month);
        String formatedDay = (dayOfMonth < 10) ? ("0" + dayOfMonth):("" + dayOfMonth);
        date.setText(year + "." + formatedMonth + "." + formatedDay);
    }
}
