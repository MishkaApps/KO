package mb.ko;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mbolg on 27.01.2017.
 */
public class Stage implements Serializable{
    private WorkActivityType type;
    private int number;
    private HashMap<Integer, Competitor> competitors;
    private long timerDuration;
    private int currentCompetitorNumber;

    public Competitor getCurrentCompetitor() {
        return currentCompetitor;
    }

    private Competitor currentCompetitor;

    public Stage(WorkActivityType type){
        this.type = type;
        competitors = new HashMap<>();
    }

    public void setCurrentCompetitorNumber(int number){
        currentCompetitorNumber = number;
    }


    public void setNumber(Integer number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public WorkActivityType getType() {
        return type;
    }

    public void setCompetitor(){
        int id = currentCompetitorNumber;
        if(competitors.containsKey(id)) {
            currentCompetitor = competitors.get(id);
            currentCompetitor.addAttempt();
        }else {
            competitors.put(id, new Competitor(id));
            currentCompetitor = competitors.get(id);
            currentCompetitor.addAttempt();
        }
    }

    public int getCompetitorNumber() {
        return currentCompetitorNumber;
    }

    public int getSummaryCompetitorsAmount() {
        return competitors.size();
    }

    public void setTimerDuration(long time) {
        this.timerDuration = time;
    }

    public long getTimerDuration() {
        return timerDuration;
    }

    public String getInfo(Context context){

        String typeRus = "|";
        Resources resources = context.getResources();
        switch(type){
            case StopwatchAndPoints:
                typeRus = resources.getString(R.string.btn_mode_time_plus_points);
                break;
            case Stopwatch:
                typeRus = resources.getString(R.string.btn_mode_time);
                break;
            case ResultPointsAndTimer:
                typeRus = resources.getString(R.string.btn_mode_result_plus_points);
                break;
            case ResultAndTimer:
                typeRus = resources.getString(R.string.btn_mode_result);
                break;
            case Pass:
                typeRus = resources.getString(R.string.btn_mode_passnpass);
                break;
            case MooseRaces:
                typeRus = resources.getString(R.string.btn_mode_moose_races);
                break;


        }
        return typeRus + "," + number;
    }
}
