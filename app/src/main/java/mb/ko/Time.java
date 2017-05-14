package mb.ko;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Created by mbolg on 07.02.2017.
 */
public class Time implements Serializable{
    private long time;
    public Time(long millis) {
        time = millis;
    }

    public Time() {
    }

    public static String getFormat(){
        return "%02d:%02d";
    }

    public long getTime() {
        return time;
    }

    public String getFormatedTime() {
        return String.format(Time.getFormat(),
                TimeUnit.MILLISECONDS.toMinutes(getTime()),
                TimeUnit.MILLISECONDS.toSeconds(getTime()) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(getTime())));
    }
    public String formatTime(long time) {
        return String.format(Time.getFormat(),
                TimeUnit.MILLISECONDS.toMinutes(time),
                TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));
    }

}
