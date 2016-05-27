package kr.werac.yeah.werac_modify;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;

import kr.werac.yeah.MyApplication;
import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class ModifyDetailHolder extends RecyclerView.ViewHolder {

    EditText edit_detail;
    Spinner spinner_area;
    Button edit_date;
    Button edit_time_s;
    Button edit_time_e;
    EditText edit_fee;
    RadioGroup radio_hasmc;
    EditText edit_lm;
    int hour_x, min_x, Year_x, Month_x, Day_x;

    public ModifyDetailHolder(View itemView) {
        super(itemView);
        edit_detail = (EditText)itemView.findViewById(R.id.edit_detail);

        spinner_area = (Spinner)itemView.findViewById(R.id.spinner_area);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MyApplication.getContext(), R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_area.setAdapter(adapter);

        edit_date = (Button)itemView.findViewById(R.id.edit_date);
        edit_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog mDPD = new DatePickerDialog(MyApplication.getContext(), myDatePickerListener, Year_x, Month_x, Day_x);
            }
        });
        edit_time_s = (Button)itemView.findViewById(R.id.edit_time_s);
        edit_time_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog myTPD = new TimePickerDialog(MyApplication.getContext(), myTimePickerListener_s, hour_x, min_x, false);
            }
        });
        edit_time_e = (Button)itemView.findViewById(R.id.edit_time_e);
        edit_time_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog myTPD = new TimePickerDialog(MyApplication.getContext(), myTimePickerListener_e, hour_x, min_x, false);
            }
        });
        radio_hasmc = (RadioGroup)itemView.findViewById(R.id.radio_hasmc);
        edit_lm = (EditText)itemView.findViewById(R.id.edit_lm);
    }

    public void setDetailWrite(WeracItem werac){
        edit_detail.setText(werac.getLocation_detail());
        edit_date.setText(werac.getDate());
        edit_time_s.setText(werac.getStart_time());
        edit_time_e.setText(werac.getEnd_time());
        edit_fee.setText(werac.getFee()+"");
        edit_lm.setText(werac.getLimit_num()+"");
    }

    protected TimePickerDialog.OnTimeSetListener myTimePickerListener_s = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_x = hourOfDay;
            min_x = minute;
            edit_time_s.setText("" + hour_x + "시" + min_x + "분");
        }
    };

    protected TimePickerDialog.OnTimeSetListener myTimePickerListener_e = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_x = hourOfDay;
            min_x = minute;
            edit_time_e.setText("" + hour_x + "시" + min_x + "분");
        }
    };

    private DatePickerDialog.OnDateSetListener myDatePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Year_x = year;
            Month_x = monthOfYear;
            Day_x = dayOfMonth;
            edit_date.setText("" + Month_x + "월" + Day_x + "일");
        }
    };
}
