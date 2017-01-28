package mb.ko;

import java.io.Serializable;

/**
 * Created by mbolg on 27.01.2017.
 */
public class Stage implements Serializable{
    private WorkActivityType type;
    private int number;

    public Stage(WorkActivityType type){
        this.type = type;
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
}
