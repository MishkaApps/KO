package mb.ko.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mb.ko.Activities.WorkActivity;
import mb.ko.R;
import mb.ko.Time;
import mb.ko.WorkFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class StopwatchFragment extends Fragment implements WorkFragment {
    private WorkActivity workActivity;


    public StopwatchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stopwatch, container, false);
    }

    @Override
    public Time getTime() {
        return null;
    }

    @Override
    public int getPoints() {
        return 0;
    }

    @Override
    public void setWorkActivity(WorkActivity workActivity) {
        this.workActivity = workActivity;
    }
}
