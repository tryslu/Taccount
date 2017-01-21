package tw.louislu.taccount;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;

import tw.louislu.taccount.Model.Event;

public class EventActivity extends AppCompatActivity {
    private static final int ZERO = 0;
    private Event _event;
    private FragmentTabHost _tabHost;
    private MenuItem _newExpensesMenuItem;

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
        final String _expense = getString(R.string.string_tabName_expense);
        final String _wallet = getString(R.string.string_tabName_wallet);
        final String _calc = getString(R.string.string_tabName_calc);
        final String _setting = getString(R.string.string_tabName_setting);
        _tabHost.addTab(_tabHost.newTabSpec(_expense).setIndicator(_expense), ExpensesFragment.class, null);
        _tabHost.addTab(_tabHost.newTabSpec(_wallet).setIndicator(_wallet), WalletFragment.class, null);
        _tabHost.addTab(_tabHost.newTabSpec(_calc).setIndicator(_calc), StatisticsFragment.class, null);
        _tabHost.addTab(_tabHost.newTabSpec(_setting).setIndicator(_setting), SettingFragment.class, null);
        _tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabSpec) {
                if(tabSpec.equals(getString(R.string.string_tabName_expense))){
                    SetMenuItemVisible(true);
                }else if(tabSpec.equals(getString(R.string.string_tabName_wallet))){
                    SetMenuItemVisible(false);
                }else if(tabSpec.equals(getString(R.string.string_tabName_calc))){
                    SetMenuItemVisible(false);
                }else if(tabSpec.equals(getString(R.string.string_tabName_setting))){
                    SetMenuItemVisible(false);
                }
            }
        });
    }

    //設定是否可看見MenuItem
    private void SetMenuItemVisible(boolean visible){
        if(_newExpensesMenuItem != null) {
            _newExpensesMenuItem.setVisible(visible);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater _inflater = getMenuInflater();
        _inflater.inflate(R.menu.actionbar, menu);
        _newExpensesMenuItem = menu.getItem(ZERO);
        return true;
    }
}
