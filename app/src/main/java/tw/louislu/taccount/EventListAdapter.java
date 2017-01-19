package tw.louislu.taccount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tw.louislu.taccount.Model.Event;

/**
 * Created on 2017/1/16.
 * Author: Louis Lu
 */

public class EventListAdapter extends BaseAdapter {
    private static final float TEXT_SIZE_TITLE = 24;
    private static final float TEXT_SIZE_DATE = 14;
    private static final int MAX_LINES = 1;
    private static final String TILDE = "~";
    private LayoutInflater _layoutInflater;
    private List<Event> _list = new ArrayList<Event>();

    public EventListAdapter(Context context, List<Event> list){
        _layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        _list = list;
    }

    @Override
    public int getCount() {
        return _list.size();
    }

    @Override
    public Event getItem(int i) {
        return _list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View _view;
        if(view == null){
            _view = _layoutInflater.inflate(R.layout.event_list_item, viewGroup, false);
        }else{
            _view = view;
        }
        TextView _textView = (TextView) _view.findViewById(R.id.eventList_itemTextView);
        _textView.setHorizontallyScrolling(true);
        _textView.setTextSize(TEXT_SIZE_TITLE);
        _textView.setMaxLines(MAX_LINES);
        _textView.setText(_list.get(i).getName());

        TextView _dateTextView = (TextView) _view.findViewById(R.id.eventList_dateTextView);
        _dateTextView.setTextSize(TEXT_SIZE_DATE);
        _dateTextView.setText(_list.get(i).getFormatStartDate() + " " + TILDE + " " + _list.get(i).getFormatEndDate());
        return _view;
    }
}
