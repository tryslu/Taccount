package tw.louislu.taccount.Model;

/**
 * Created on 2017/1/15.
 * Author: Louis Lu
 */

public class Expense {
    private int _id;
    private String _name;
    private int _cost;
    private Currency _currency;
    private int _eventId;

    public Expense(String name, int cost, Currency currency, int eventId){
        this._name = name;
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

    public String getName(){
        return _name;
    }

    public void setName(String name){
        this._name = name;
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
