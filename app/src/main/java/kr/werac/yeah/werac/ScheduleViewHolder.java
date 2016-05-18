package kr.werac.yeah.werac;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import kr.werac.yeah.R;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class ScheduleViewHolder extends RecyclerView.ViewHolder {

    EditText edit_schedule;
    Button btn_add_sch;
    public ScheduleViewHolder(View itemView) {
        super(itemView);
        edit_schedule = (EditText)itemView.findViewById(R.id.edit_schedule);
        btn_add_sch = (Button)itemView.findViewById(R.id.btn_add_sch);
    }
}
