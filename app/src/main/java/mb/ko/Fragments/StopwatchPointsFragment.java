package mb.ko.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.Choreographer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.EditText;

import mb.ko.R;
import mb.ko.WorkFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class StopwatchPointsFragment extends Fragment implements WorkFragment {
    private Chronometer stopwatch;
    private EditText etPoints;

    public StopwatchPointsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stopwatch_points, container, false);
        stopwatch = (Chronometer)view.findViewById(R.id.stopwatch);
        etPoints = (EditText)view.findViewById(R.id.etPoints);

        return view;
    }

    @Override
    public int getTime() {
        return 0;
    }

    @Override
    public int getPoints() {
        return  Integer.parseInt(etPoints.getText().toString());
    }
}
