package tw.louislu.taccount;

import android.app.DatePickerDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NewEventActivity extends AppCompatActivity {
    private final static String TAG = "Debug_Message";
    private EditText _eventNameEditText;
    private EditText _eventStartDateEditText;
    private EditText _eventEndDateEditText;
    private java.util.Calendar _calender = java.util.Calendar.getInstance();
    private Date _startDate;
    private Date _endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        initToolBar();
        initFindViewById();
    }

    private void initToolBar(){
        Toolbar toolBar = (Toolbar) findViewById(R.id.newEvent_Toolbar);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initFindViewById(){
        _eventNameEditText = (EditText) findViewById(R.id.newEvent_eventNameEditText);
        _eventStartDateEditText = (EditText) findViewById(R.id.newEvent_startDateEditText);
        _eventEndDateEditText = (EditText) findViewById(R.id.newEvent_endDateEditText);
    }

    private DatePickerDialog.OnDateSetListener startDatePicker = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            Date _date = getSelectedDate(year, monthOfYear, dayOfMonth);
            _startDate = _date;
            _eventStartDateEditText.setText(getFormatDate(_date));
        }
    };

    private DatePickerDialog.OnDateSetListener endDatePicker = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            Date _date = getSelectedDate(year, monthOfYear, dayOfMonth);
            _endDate = _date;
            _eventEndDateEditText.setText(getFormatDate(_date));
        }
    };

    private String getFormatDate(Date date){
        String _format = getString(R.string.string_format_date);
        SimpleDateFormat _simpleDateFormat = new SimpleDateFormat(_format, Locale.TAIWAN);
        return _simpleDateFormat.format(date);
    }

    private Date getSelectedDate(int year, int monthOfYear, int dayOfMonth){
        _calender.set(Calendar.YEAR, year);
        _calender.set(Calendar.MONTH, monthOfYear);
        _calender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        return _calender.getTime();
    }

    private void showDatePickerDialog(DatePickerDialog.OnDateSetListener datePicker){
        DatePickerDialog _dialog = new DatePickerDialog(NewEventActivity.this,
                datePicker,
                _calender.get(java.util.Calendar.YEAR),
                _calender.get(java.util.Calendar.MONTH),
                _calender.get(java.util.Calendar.DAY_OF_MONTH));
        _dialog.show();
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.newEvent_startDateEditText:
                showDatePickerDialog(startDatePicker);
                return;
            case R.id.newEvent_endDateEditText:
                showDatePickerDialog(endDatePicker);
                return;
            default:
        }
    }

}
