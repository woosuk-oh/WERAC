package kr.werac.yeah.werac_modify;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class ModifyDetailHolder extends RecyclerView.ViewHolder {

    EditText edit_detail;
    Spinner spinner_area;
    EditText edit_date;
    EditText edit_time;
    EditText edit_fee;
    RadioGroup radio_hasmc;
    EditText edit_lm;

    public ModifyDetailHolder(View itemView) {
        super(itemView);
        edit_detail = (EditText)itemView.findViewById(R.id.edit_detail);
        spinner_area = (Spinner)itemView.findViewById(R.id.spinner_area);
        edit_date = (EditText)itemView.findViewById(R.id.edit_date);
        edit_time = (EditText)itemView.findViewById(R.id.edit_time);
        edit_fee = (EditText)itemView.findViewById(R.id.edit_fee);
        radio_hasmc = (RadioGroup)itemView.findViewById(R.id.radio_hasmc);
        edit_lm = (EditText)itemView.findViewById(R.id.edit_lm);
    }

    public void setDetailWrite(WeracItem werac){
        edit_detail.setText(werac.getLocation_detail());
        edit_date.setText(werac.getDate());
        edit_time.setText(werac.getStart_time() + "~" + werac.getEnd_time());
        edit_fee.setText(werac.getFee()+"");
        edit_lm.setText(werac.getLimit_num()+"");
    }
}
