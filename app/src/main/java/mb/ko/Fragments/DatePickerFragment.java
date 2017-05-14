package mb.ko.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

/**
 * Created by mbolg on 09.05.2017.
 */
public class DatePickerFragment extends DialogFragment {
    private int year, month, day;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d("my log", "onCreateDialog");
        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
    }


    public void setDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
}
