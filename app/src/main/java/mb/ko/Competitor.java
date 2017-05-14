package mb.ko;

import android.util.Log;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by mbolg on 05.02.2017.
 */
public class Competitor implements Serializable {

    private int id;
    private Time time;
    private int result;
    private int points;
    private boolean success;
    private static long HOUR_IN_MILLISECONDS = 3600000L;
    private int attempt;

    public Competitor(int id) {
        this.id = id;
        attempt = 0;
    }

    public int getId() {
        return id;
    }

    public String getTime() {
        if (time == null)
            return "-";
        else if (time.getTime() > HOUR_IN_MILLISECONDS){
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time.getTime());
            Log.d("my log", "time:" + formatter.format(calendar.getTime()) + "; millis: " + time.getTime());
            return formatter.format(calendar.getTime());
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time.getTime());
            Log.d("my log", "time:" + formatter.format(calendar.getTime()) + "; millis: " + time.getTime());
            return "00:" + formatter.format(calendar.getTime());
        }
    }

    public int getResult() {
        return result;
    }

    public int getPoints() {
        return points;
    }

    public String getSuccess() {
        return success ? "Не Прошел" : "Прошел";
    }

    public String getAllResult() {
        return getId() + "," + getTime() + "," + getResult() + "," + getPoints() + "," + getSuccess() + "," + attempt;
    }


    public void setPoints(int points) {
        this.points = points;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getNumber() {
        return id;
    }

    public void addAttempt() {
        attempt++;
    }
}
