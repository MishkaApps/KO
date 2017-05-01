package mb.ko;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Created by mbolg on 05.02.2017.
 */
public class Competitor implements Serializable{

    private int id;
    private long startTime, finishTime;
    private Time time;
    private int result;
    private int points;
    private boolean success;

    public Competitor(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public String getStartTime(){
        return "13:43";
    }
    public String getFinishTime(){
        return "13:43";
    }
    public String getTime() {
        if(time == null)
            return "";
        return String.format(Time.getFormat(),
                TimeUnit.MILLISECONDS.toMinutes(time.getTime()),
                TimeUnit.MILLISECONDS.toSeconds(time.getTime()) - TimeUnit.MILLISECONDS.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time.getTime())));
    }
    public int getResult() {
        return result;
    }
    public int getPoints() {
        return points;
    }
    public String getSuccess(){return success?"1":"0";}

    public String getAllResult(){
        return getId() + "," + getStartTime() + "," + getFinishTime() + "," + getTime() + "," + getResult() + "," + getPoints() + "," + getSuccess();
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
}
