package tw.louislu.taccount.Model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created on 2017/1/15.
 * Author: Louis Lu
 */

public class Event {
    private static final String REGEX_SLASH = "/";
    private String _name;
    private Date _startDate;
    private Date _endDate;
    private Calendar _calendar = Calendar.getInstance();

    public Event(){
        this._name = "default";
        this._startDate = _calendar.getTime();
        this._endDate = _calendar.getTime();
    }

    public Event(String name, String startDate, String endDate){
        this._name = name;
        this._startDate = getDateByString(startDate);
        this._endDate = getDateByString(endDate);
    }

    //把yyyy/MM/dd格式的String轉成Date物件
    private Date getDateByString(String date){
        String[] _split = date.split(REGEX_SLASH);
        _calendar.set(Calendar.YEAR, Integer.valueOf(_split[0]));
        _calendar.set(Calendar.MONTH, Integer.valueOf(_split[1]));
        _calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(_split[2]));
        return _calendar.getTime();
    }

    public String getName(){
        return _name;
    }

    public void setName(String name){
        this._name = name;
    }

    public Date getStartDate(){
        return _startDate;
    }

    public void setStartDate(String date){
        this._startDate = getDateByString(date);
    }

    public Date getEndDate(){
        return _endDate;
    }

    public void setEndDate(String date){
        this._endDate = getDateByString(date);
    }
}
