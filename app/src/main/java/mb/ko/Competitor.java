package mb.ko;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Created by mbolg on 05.02.2017.
 */
public class Competitor implements Serializable{

    private int id;
    private int points;
    private Time time;
    private int result;
    private boolean passed;
    private boolean success;

    public Competitor(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return id;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getTime() {
        return String.format(Time.getFormat(),
                TimeUnit.MILLISECONDS.toMinutes(time.getTime()),
                TimeUnit.MILLISECONDS.toSeconds(time.getTime()) - TimeUnit.MILLISECONDS.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time.getTime())));
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    public void setPass(boolean passed) {
        this.passed = passed;
    }

    public boolean getPassed() {
        return passed;
    }

    public String getPassedString() {
        return passed?"Прошел":"Не прошел";
    }


}
