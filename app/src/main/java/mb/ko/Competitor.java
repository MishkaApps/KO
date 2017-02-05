package mb.ko;

import java.io.Serializable;

/**
 * Created by mbolg on 05.02.2017.
 */
public class Competitor implements Serializable{

    private int id;
    private int points;

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
}
