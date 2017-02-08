package tw.louislu.taccount.Model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created on 2017/1/15.
 * Author: Louis Lu
 */

public class Taccount {
    private List<Event> _eventList = new LinkedList<Event>();

    public Taccount(){}

    public void addEvent(Event event){
        // insert a new event to db
        ((LinkedList<Event>)_eventList).addFirst(event);
    }

    public List<Event> getEventList(){
        return _eventList;
    }
}
