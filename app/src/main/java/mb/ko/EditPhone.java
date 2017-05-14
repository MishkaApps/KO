package mb.ko;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

/**
 * Created by mbolg on 10.05.2017.
 */
public class EditPhone extends EditText {

    public EditPhone(Context context, AttributeSet attrs) {
        super(context, attrs);
        setText("+7-");
        addTextChangedListener(new PhonenumberController(this));
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        setSelection(getText().length());

    }

    private class PhonenumberController implements TextWatcher {
        private EditText field;
        private String previousString;

        public PhonenumberController(EditText editText) {
            field = editText;
            previousString = field.getText().toString();
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        //+7-931-282-1239
        @Override
        public void afterTextChanged(Editable s) {
            String newString = s.toString();
            Log.d("my log", previousString + ";" + newString);
            if (s.toString().equals("+7-")) {
                field.setSelection(3);
                previousString = field.getText().toString();
                return;
            }

            if (newString.length() < previousString.length())
                if (previousString.toCharArray()[previousString.length()-1] == '-') {
                    Log.d("my log", "correct:" + newString.substring(0, newString.length()-1));
                    previousString = newString;
                    field.setText(newString.substring(0, newString.length()-1));
                    return;
                }

            if (s.length() < 4) {
                field.setText("+7-");
                previousString = field.getText().toString();
                return;
            }
            if (s.length() == 6) {
                field.setText(s + "-");
                field.setSelection(7);
                previousString = field.getText().toString();
                return;
            }
            if (s.length() == 10) {
                field.setText(s + "-");
                field.setSelection(11);
                previousString = field.getText().toString();
                return;
            }
            if (s.length() == 13) {
                field.setText(s + "-");
                field.setSelection(14);
                previousString = field.getText().toString();
                return;
            }
            previousString = field.getText().toString();
        }
    }
}
