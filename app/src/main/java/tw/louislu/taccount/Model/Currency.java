package tw.louislu.taccount.Model;

/**
 * Created on 2017/1/15.
 * Author: Louis Lu
 */

public class Currency {
    private String _name;

    public Currency(){
        this._name = "";
    }

    public Currency(String name){
        this._name = name;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }
}
