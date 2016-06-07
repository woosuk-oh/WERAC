package kr.werac.yeah.werac_create;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

import kr.werac.yeah.MyApplication;
import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class CreateDetailHolder extends RecyclerView.ViewHolder {

    EditText edit_detail;
    Spinner spinner_area;
    TextView edit_date;
    TextView edit_time_s;
    TextView edit_time_e;
    EditText edit_fee;
    RadioGroup radio_hasmc;
    EditText edit_lm;
    WeracItem werac;
    ArrayAdapter<CharSequence> adapter;
    Button btn_add_sch;

    public interface OnDateClickListener {
        void onItemClick(View view);
    }

    public interface OnTimeClickListener {
    void onItemClick(View view, int SorE);
    }

    OnDateClickListener mDateListener;
    public void setOnDateClickListener(OnDateClickListener listener) {
        mDateListener = listener;
    }

    OnTimeClickListener mTimeListener;
    public void setOnTimeClickListener(OnTimeClickListener listener) {
        mTimeListener = listener;
    }

    public interface OnSchClickListener {
        void onItemClick(View view);
    }

    OnSchClickListener mListener_sch;
    public void setOnSchClickListener(OnSchClickListener listener) {
        mListener_sch = listener;
    }

    public CreateDetailHolder(View itemView) {
        super(itemView);
        werac = new WeracItem();
        edit_detail = (EditText)itemView.findViewById(R.id.edit_detail);

        spinner_area = (Spinner)itemView.findViewById(R.id.spinner_area);
        adapter = ArrayAdapter.createFromResource(MyApplication.getContext(), R.array.spinner_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_area.setAdapter(adapter);

        edit_date = (TextView) itemView.findViewById(R.id.edit_date);
        edit_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDateListener != null) {
                    mDateListener.onItemClick(v);
                }
            }
        });
        Calendar myCal = Calendar.getInstance();
        edit_date.setText("" + myCal.get(Calendar.YEAR) + "년" +  myCal.get(Calendar.MONTH) + "월" + myCal.get(Calendar.DAY_OF_MONTH) + "일");

        edit_time_s = (TextView)itemView.findViewById(R.id.edit_time_s);
        edit_time_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimeListener != null) {
                    mTimeListener.onItemClick(v, 1);
                }
            }
        });
        edit_time_e = (TextView)itemView.findViewById(R.id.edit_time_e);
        edit_time_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimeListener != null) {
                    mTimeListener.onItemClick(v, 2);
                }
            }
        });
        edit_fee = (EditText)itemView.findViewById(R.id.edit_fee);
        radio_hasmc = (RadioGroup)itemView.findViewById(R.id.radio_hasmc);
        edit_lm = (EditText)itemView.findViewById(R.id.edit_lm);

        btn_add_sch = (Button)itemView.findViewById(R.id.btn_add_sch);
        btn_add_sch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener_sch != null){
                    mListener_sch.onItemClick(v);
                }
            }
        });
    }


    public WeracItem getDetail() {
        werac.setLocation_detail(edit_detail.getText().toString());
        werac.setLocation_area(spinner_area.getSelectedItem().toString());
        werac.setDate(edit_date.getText().toString());
        werac.setStart_time(edit_time_s.getText().toString());
        werac.setEnd_time(edit_time_e.getText().toString());
        if((edit_fee.getText()+"").toString() != "")
            werac.setFee(Integer.parseInt((edit_fee.getText()+"").toString()));
        if (radio_hasmc.getCheckedRadioButtonId() == 0)
            werac.setHas_mc(true);
        else
            werac.setHas_mc(false);
        if((edit_lm.getText()+"").toString() != "")
            werac.setLimit_num(Integer.parseInt((edit_lm.getText()+"").toString()));
        return werac;
    }

    public void setDate(int year, int monthOfYear, int dayOfMonth){
        int month = monthOfYear + 1;
        edit_date.setText("" + year + "년" + month + "월" + dayOfMonth + "일");
    }

    public void setTime(int SorE, int hour_x, int min_x){

        if(SorE == 1) {
            if(min_x == 0)
                edit_time_s.setText("" + hour_x + "시" + min_x + "0분 부터");
            else
                edit_time_s.setText("" + hour_x + "시" + min_x + "분 부터");
        }else {
            if(min_x == 0)
                edit_time_e.setText("" + hour_x + "시" + min_x + "0분 까지");
            else
                edit_time_e.setText("" + hour_x + "시" + min_x + "분 까지");
        }
    }
}
