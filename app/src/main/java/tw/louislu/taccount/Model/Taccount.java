package tw.louislu.taccount.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/1/15.
 * Author: Louis Lu
 */

public class Taccount {
    private List<Event> _eventList = new ArrayList<Event>();

    public Taccount(){}

    public void addEvent(Event event){
        _eventList.add(event);
    }

    public List<Event> getEventList(){
        return _eventList;
    }
}
