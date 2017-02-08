package tw.louislu.taccount.Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created on 2017/1/15.
 * Author: Louis Lu
 */

public class Event implements Serializable {
    private static final String REGEX_SLASH = "/";
    private static final String FORMAT_DATE = "yyyy/MM/dd";
    private int _id;
    private String _name;
    private Calendar _calendar = Calendar.getInstance();
    private Date _startDate;
    private Date _endDate;
    private List<Expense> _expenses = new ArrayList<Expense>();

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

    //取得yyyy/MM/dd格式的日期字串
    private String getFormatDate(Date date){
        SimpleDateFormat _simpleDateFormat = new SimpleDateFormat(FORMAT_DATE, Locale.TAIWAN);
        return _simpleDateFormat.format(date);
    }

    //新增一筆支出
    public void addExpense(String name, int cost, Currency currency){
        // insert a new expense to db
        _expenses.add(new Expense(name, cost, currency, _id));
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

    public String getFormatStartDate(){
        return getFormatDate(_startDate);
    }

    public void setStartDate(String date){
        this._startDate = getDateByString(date);
    }

    public Date getEndDate(){
        return _endDate;
    }

    public String getFormatEndDate(){
        return getFormatDate(_endDate);
    }

    public void setEndDate(String date){
        this._endDate = getDateByString(date);
    }

    public int getId(){
        return _id;
    }

    public void setId(int id){
        this._id = id;
    }
}
