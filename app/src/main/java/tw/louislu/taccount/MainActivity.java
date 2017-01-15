package tw.louislu.taccount;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import tw.louislu.taccount.Model.Event;
import tw.louislu.taccount.Model.Taccount;

public class MainActivity extends AppCompatActivity {
    public static final String KEY_EVENT_NAME = "key_event_name";
    public static final String KEY_EVENT_START_DATE = "key_event_start_date";
    public static final String KEY_EVENT_END_DATE = "key_event_end_date";
    private static final String TAG = "Debug_Message";
    private static final int NEW_EVENT_REQUEST = 1; // The request code
    private Taccount _taccount = new Taccount();
    private ListView _eventListView;
    private EventListAdapter _eventListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBar();
        initFindViewById();
        setListAdapter();
    }

    //初始化工具列(ActionBar)
    private void initToolBar(){
        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);
    }

    //取得view裡的元件
    private void initFindViewById(){
        _eventListView = (ListView) findViewById(R.id.main_eventListView);
    }

    //設定ListAdapter
    private void setListAdapter(){
        _eventListViewAdapter = new EventListAdapter(this, _taccount.getEventList());
        _eventListView.setAdapter(_eventListViewAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case NEW_EVENT_REQUEST:
                if(resultCode == RESULT_OK){
                    Bundle _bundle = data.getExtras();
                    Log.d(TAG, "onActivityResult: event name = " + _bundle.getString(KEY_EVENT_NAME));
                    Log.d(TAG, "onActivityResult: event start date = " + _bundle.getString(KEY_EVENT_START_DATE));
                    Log.d(TAG, "onActivityResult: event end data = " + _bundle.getString(KEY_EVENT_END_DATE));
                    Event _event = new Event(_bundle.getString(MainActivity.KEY_EVENT_NAME),
                            _bundle.getString(MainActivity.KEY_EVENT_START_DATE),
                            _bundle.getString(MainActivity.KEY_EVENT_END_DATE));
                    _taccount.addEvent(_event);
                    _eventListViewAdapter.notifyDataSetChanged();
                }
                return;
            default:
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()){
            case R.id.action_new:
                // New Event
                Intent _intent = new Intent(this, NewEventActivity.class);
                startActivityForResult(_intent, NEW_EVENT_REQUEST);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater _inflater = getMenuInflater();
        _inflater.inflate(R.menu.actionbar, menu);
        return true;
    }
}
