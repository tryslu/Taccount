package tw.louislu.taccount.Model;

/**
 * Created on 2017/1/15.
 * Author: Louis Lu
 */

public class Currency {
    private int _id;
    private String _name;

    public Currency(){
        this._id = -1;
        this._name = "";
    }

    public Currency(String name){
        this._name = name;
    }

    public Currency(int id, String name){
        this._id = id;
        this._name = name;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public int getId(){
        return _id;
    }

    public void setId(int id){
        this._id = id;
    }
}
