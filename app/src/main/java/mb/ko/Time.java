package mb.ko;

import java.io.Serializable;

/**
 * Created by mbolg on 07.02.2017.
 */
public class Time implements Serializable{
    private long time;
    public Time(long millis) {
        time = millis;
    }
    public static String getFormat(){
        return "%d:%d";
    }

    public long getTime() {
        return time;
    }
}
