package mb.ko;

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

    public Competitor getCurrentCompetitor() {
        return currentCompetitor;
    }

    private Competitor currentCompetitor;

    public Stage(WorkActivityType type){
        this.type = type;
        competitors = new HashMap<>();
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

    public void setCompetitor(int id){
        if(competitors.containsKey(id))
            currentCompetitor = competitors.get(id);
        else {
            competitors.put(id, new Competitor(id));
            currentCompetitor = competitors.get(id);
        }
    }

    public int getCompetitorNumber() {
        return currentCompetitor.getNumber();
    }

    public int getSummaryCompetitorsAmount() {
        return competitors.size();
    }

}
