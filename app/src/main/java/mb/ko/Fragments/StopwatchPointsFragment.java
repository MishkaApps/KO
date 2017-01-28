package mb.ko.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mb.ko.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class StopwatchPointsFragment extends Fragment {


    public StopwatchPointsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stopwatch_points, container, false);
    }

}
