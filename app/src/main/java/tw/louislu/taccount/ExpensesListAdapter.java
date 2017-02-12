package tw.louislu.taccount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tw.louislu.taccount.Model.Expense;

/**
 * Created on 2017/2/13.
 * Author: Louis Lu
 */

public class ExpensesListAdapter extends BaseAdapter {
    private static final String DOLLAR = " 元";
    private LayoutInflater _layoutInflater;
    private List<Expense> _list = new ArrayList<Expense>();

    public ExpensesListAdapter(Context context, List<Expense> list) {
        _layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        _list = list;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View _view;
        if(view == null){
            _view = _layoutInflater.inflate(R.layout.expenses_list_item, viewGroup, false);
        }else{
            _view = view;
        }
        TextView _titleTextView = (TextView) _view.findViewById(R.id.expensesListItem_titleTextView);
        TextView _currencyTextView = (TextView) _view.findViewById(R.id.expensesListItem_currencyTextView);
        TextView _costTextView = (TextView) _view.findViewById(R.id.expensesListItem_costTextView);
        _titleTextView.setText(_list.get(i).getTitle());
        _currencyTextView.setText(_list.get(i).getCurrency().getName());
        _costTextView.setText(String.valueOf(_list.get(i).getCost()) + DOLLAR);
        return _view;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Object getItem(int i) {
        return _list.get(i);
    }

    @Override
    public int getCount() {
        return _list.size();
    }
}
