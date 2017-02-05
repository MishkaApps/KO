package mb.ko.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mb.ko.Activities.TimerActivity;
import mb.ko.R;
import mb.ko.WorkFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultPointsTimerFragment extends Fragment implements View.OnClickListener, WorkFragment {
    private Button btnTimer;

    public ResultPointsTimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result_points_timer, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnTimer = (Button) getView().findViewById(R.id.btnResultPointsTimerTimer);
        btnTimer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), TimerActivity.class);
        startActivity(intent);
    }

    @Override
    public int getTime() {
        return 0;
    }

    @Override
    public int getPoints() {
        return 0;
    }
}
