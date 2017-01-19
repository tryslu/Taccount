package tw.louislu.taccount;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import tw.louislu.taccount.Model.Event;
import tw.louislu.taccount.Model.Taccount;

public class MainActivity extends AppCompatActivity {
    public static final String KEY_EVENT = "key_event";
    private static final String TAG = "Debug_Message";
    private static final int NEW_EVENT_REQUEST = 1; // The request code
    private ListView _eventListView;
    private EventListAdapter _eventListViewAdapter;
    private Taccount _taccount = new Taccount();

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
        _eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    Event _event = _taccount.getEventList().get(i);
                    Bundle _bundle = new Bundle();
                    _bundle.putSerializable(KEY_EVENT, _event);
                    Intent _intent = new Intent(MainActivity.this, EventActivity.class);
                    _intent.putExtras(_bundle);
                    startActivity(_intent);
                }catch (Exception e){}
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case NEW_EVENT_REQUEST:
                if(resultCode == RESULT_OK){
                    _taccount.addEvent((Event) data.getExtras().getSerializable(KEY_EVENT));
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
                try{
                    Intent _intent = new Intent(this, NewEventActivity.class);
                    startActivityForResult(_intent, NEW_EVENT_REQUEST);
                }catch (Exception e){}
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
