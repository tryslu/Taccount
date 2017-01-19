package tw.louislu.taccount;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import tw.louislu.taccount.Model.Event;

public class EventActivity extends AppCompatActivity {
    private Event _event;
    FragmentTabHost _tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        _event = (Event)getIntent().getExtras().getSerializable(MainActivity.KEY_EVENT);
        initToolBar();
        initTabHost();

    }

    //初始化工具列(ActionBar)
    private void initToolBar(){
        Toolbar toolBar = (Toolbar) findViewById(R.id.event_Toolbar);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(_event.getName());
    }

    //初始化及設定TabHost
    private void initTabHost(){
        _tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        _tabHost.setup(this, getSupportFragmentManager(), R.id.container);
        String _expense = getString(R.string.string_tabName_expense);
        String _wallet = getString(R.string.string_tabName_wallet);
        String _calc = getString(R.string.string_tabName_calc);
        String _setting = getString(R.string.string_tabName_setting);
        _tabHost.addTab(_tabHost.newTabSpec(_expense).setIndicator(_expense), ExpensesFragment.class, null);
        _tabHost.addTab(_tabHost.newTabSpec(_wallet).setIndicator(_wallet), WalletFragment.class, null);
    }
}
