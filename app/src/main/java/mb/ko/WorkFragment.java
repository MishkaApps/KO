package mb.ko;

import mb.ko.Activities.WorkActivity;

/**
 * Created by mbolg on 05.02.2017.
 */
public interface WorkFragment {
    Time getTime();
    int getPoints();
    void setWorkActivity(WorkActivity workActivity);
}
