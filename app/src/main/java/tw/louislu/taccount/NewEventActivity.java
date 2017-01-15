package tw.louislu.taccount;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NewEventActivity extends AppCompatActivity {
    private static final String TAG = "Debug_Message";
    private static final int ZERO = 0;
    private EditText _eventNameEditText;
    private EditText _eventStartDateEditText;
    private EditText _eventEndDateEditText;
    private Button _eventCreateButton;
    private Calendar _calender = Calendar.getInstance();
    private Date _startDate;
    private Date _endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        initToolBar();
        initFindViewById();
        setDefaultDate();
        checkEventName();

    }

    //初始化工具列(ActionBar)
    private void initToolBar(){
        Toolbar toolBar = (Toolbar) findViewById(R.id.newEvent_Toolbar);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    //取得view裡的元件
    private void initFindViewById(){
        _eventNameEditText = (EditText) findViewById(R.id.newEvent_eventNameEditText);
        _eventStartDateEditText = (EditText) findViewById(R.id.newEvent_startDateEditText);
        _eventEndDateEditText = (EditText) findViewById(R.id.newEvent_endDateEditText);
        _eventCreateButton = (Button) findViewById(R.id.newEvent_eventCreateButton);
    }

    //預設日期
    private void setDefaultDate(){
        Date _date = getSelectedDate(_calender.get(Calendar.YEAR), _calender.get(Calendar.MONTH), _calender.get(Calendar.DAY_OF_MONTH));
        _startDate = _date;
        _endDate = _date;
        _eventStartDateEditText.setText(getFormatDate(_date));
        _eventEndDateEditText.setText(getFormatDate(_date));
    }

    //確認名稱是否空白
    private void checkEventName(){
        _eventCreateButton.setClickable(false);
        _eventNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() != ZERO){
                    _eventCreateButton.setClickable(true);
                }else{
                    _eventCreateButton.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    //確認名稱、日期是否合理
    private boolean isInputLegal(){
        if(_startDate.after(_endDate)){
            showDialog(R.string.string_dateErrorTitle, R.string.string_dateErrorMessage);
            return false;
        }
        if(_eventNameEditText.getText().toString().isEmpty()){
            showDialog(R.string.string_eventNameErrorTitle, R.string.string_eventNameErrorMessage);
            return false;
        }
        return true;
    }

    //顯示Dialog
    private void showDialog(int titleId, int messageId){
        AlertDialog.Builder _builder = new AlertDialog.Builder(NewEventActivity.this);
        _builder.setTitle(titleId)
                .setMessage(messageId)
                .setPositiveButton(R.string.string_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }

    //開始日期的DatePicker
    private DatePickerDialog.OnDateSetListener startDatePicker = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            Date _date = getSelectedDate(year, monthOfYear, dayOfMonth);
            _startDate = _date;
            _eventStartDateEditText.setText(getFormatDate(_date));
        }
    };

    //結束日期的DatePicker
    private DatePickerDialog.OnDateSetListener endDatePicker = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            Date _date = getSelectedDate(year, monthOfYear, dayOfMonth);
            _endDate = _date;
            _eventEndDateEditText.setText(getFormatDate(_date));
        }
    };

    //取得yyyy/MM/dd格式的日期字串
    private String getFormatDate(Date date){
        String _format = getString(R.string.string_format_date);
        SimpleDateFormat _simpleDateFormat = new SimpleDateFormat(_format, Locale.TAIWAN);
        return _simpleDateFormat.format(date);
    }

    //取得選好的日期的Date物件
    private Date getSelectedDate(int year, int monthOfYear, int dayOfMonth){
        _calender.set(Calendar.YEAR, year);
        _calender.set(Calendar.MONTH, monthOfYear);
        _calender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        return _calender.getTime();
    }

    //顯示日期選擇Dialog
    private void showDatePickerDialog(DatePickerDialog.OnDateSetListener datePicker){
        DatePickerDialog _dialog = new DatePickerDialog(NewEventActivity.this,
                datePicker,
                _calender.get(java.util.Calendar.YEAR),
                _calender.get(java.util.Calendar.MONTH),
                _calender.get(java.util.Calendar.DAY_OF_MONTH));
        _dialog.show();
    }

    //建立使用者輸入的資料的Bundle
    private Bundle getDataBundle(){
        Bundle _bundle = new Bundle();
        _bundle.putString(MainActivity.KEY_EVENT_NAME, _eventNameEditText.getText().toString());
        _bundle.putString(MainActivity.KEY_EVENT_START_DATE, _eventStartDateEditText.getText().toString());
        _bundle.putString(MainActivity.KEY_EVENT_END_DATE, _eventEndDateEditText.getText().toString());
        return _bundle;
    }

    //按鈕及日期欄位的onClick事件
    public void onClick(View view){
        switch (view.getId()){
            case R.id.newEvent_startDateEditText:
                showDatePickerDialog(startDatePicker);
                return;
            case R.id.newEvent_endDateEditText:
                showDatePickerDialog(endDatePicker);
                return;
            case R.id.newEvent_eventCreateButton:
                if(isInputLegal()){
                    Intent _intent = new Intent();
                    _intent.putExtras(getDataBundle());
                    setResult(RESULT_OK, _intent);
                    finish();
                }
                return;
            default:
        }
    }

}
