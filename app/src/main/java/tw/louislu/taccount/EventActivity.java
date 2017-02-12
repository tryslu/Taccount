package tw.louislu.taccount;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;

import java.util.List;

import tw.louislu.taccount.Model.Currency;
import tw.louislu.taccount.Model.Event;
import tw.louislu.taccount.Model.Expense;

public class EventActivity extends AppCompatActivity {
    private static final int ZERO = 0;
    private static final int INT_MAX = 2147483647;
    private static final String STRING_EMPTY = "";
    private Event _event;
    private FragmentTabHost _tabHost;
    private MenuItem _newExpensesMenuItem;
    private AlertDialog _newExpenseDialog;
    private String _expenseString, _walletString, _calcString, _settingString;
    private Currency _ntd = new Currency("新臺幣"); //暫時

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
        _expenseString = getString(R.string.string_tabName_expense);
        _walletString = getString(R.string.string_tabName_wallet);
        _calcString = getString(R.string.string_tabName_calc);
        _settingString = getString(R.string.string_tabName_setting);
        _tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        _tabHost.setup(this, getSupportFragmentManager(), R.id.container);
        _tabHost.addTab(_tabHost.newTabSpec(_expenseString).setIndicator(_expenseString), ExpensesFragment.class, null);
        _tabHost.addTab(_tabHost.newTabSpec(_walletString).setIndicator(_walletString), WalletFragment.class, null);
        _tabHost.addTab(_tabHost.newTabSpec(_calcString).setIndicator(_calcString), StatisticsFragment.class, null);
        _tabHost.addTab(_tabHost.newTabSpec(_settingString).setIndicator(_settingString), SettingFragment.class, null);
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

    //顯示新增支出Dialog
    private void ShowNewExpenseDialog(){
        final View _view = LayoutInflater.from(EventActivity.this).inflate(R.layout.dialog_new_expense, null);
        final EditText _costEditText = (EditText) _view.findViewById(R.id.dialog_costEditText);
        final EditText _titleEditText = (EditText) _view.findViewById(R.id.dialog_titleEditText);
        final EditText _contentEditText = (EditText) _view.findViewById(R.id.dialog_contentEditText);
        final Button _completeButton = (Button) _view.findViewById(R.id.dialog_completeButton);
        _completeButton.setEnabled(false);
        _costEditText.setFilters(new InputFilter[]{ new InputFilterMinMax(ZERO, INT_MAX) });
        _costEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                _completeButton.setEnabled(false);
                if(charSequence.length() != ZERO){
                    if(_titleEditText.length() != ZERO){
                        _completeButton.setEnabled(true);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        _titleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                _completeButton.setEnabled(false);
                if(charSequence.length() != ZERO){
                    if(_costEditText.length() != ZERO){
                        _completeButton.setEnabled(true);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        _completeButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                if((_costEditText.length() != ZERO) && (_titleEditText.length() != ZERO)){
                    _event.addExpense(_titleEditText.getText().toString(),
                            _contentEditText.getText().toString(),
                            Integer.parseInt(_costEditText.getText().toString()),
                            _ntd);
                    ((ExpensesFragment)getSupportFragmentManager().findFragmentByTag(_expenseString)).notifyDataSetChanged();
                    if(_newExpenseDialog != null){
                        if(_newExpenseDialog.isShowing())
                            _newExpenseDialog.dismiss();
                    }
                }
            }
        });
        _newExpenseDialog = new AlertDialog.Builder(EventActivity.this)
                .setTitle(R.string.string_dialog_title)
                .setView(_view)
                .setNegativeButton(R.string.string_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }

    //取得事件的支出list
    public List<Expense> getExpensesList(){
        return _event.getExpensesList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater _inflater = getMenuInflater();
        _inflater.inflate(R.menu.actionbar, menu);
        _newExpensesMenuItem = menu.getItem(ZERO);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()){
            case R.id.action_new:
                // New Expense
                try{
                    // show dialog and get expense title, content, cost
                    ShowNewExpenseDialog();
                }catch (Exception e){}
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
