package tw.louislu.taccount.Model;

/**
 * Created on 2017/1/15.
 * Author: Louis Lu
 */

public class Expense {
    private int _id;
    private String _title;
    private String _content;
    private int _cost;
    private Currency _currency;
    private int _eventId;

    public Expense(String title, String content, int cost, Currency currency, int eventId){
        this._title = title;
        this._content = content;
        this._cost = cost;
        this._currency = currency;
        this._eventId = eventId;
    }

    public int getId(){
        return _id;
    }

    public void setId(int id){
        this._id = id;
    }

    public String getTitle(){
        return _title;
    }

    public void setTitle(String title){
        this._title = title;
    }

    public String getContent(){
        return _content;
    }

    public void setContent(String content){
        this._content = content;
    }

    public int getCost(){
        return _cost;
    }

    public void setCost(int cost){
        this._cost = cost;
    }

    public Currency getCurrency(){
        return _currency;
    }

    public void setCurrency(Currency currency){
        this._currency = currency;
    }

    public int getEventId(){
        return _eventId;
    }

    public void setEventId(int eventId){
        this._eventId = eventId;
    }
}
