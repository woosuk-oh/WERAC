package kr.werac.yeah.werac_modify;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import kr.werac.yeah.MyApplication;
import kr.werac.yeah.R;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class ModifyScheduleHolder extends RecyclerView.ViewHolder {

    TextView et_sch;

    public ModifyScheduleHolder(View itemView) {
        super(itemView);
        et_sch = (TextView)itemView.findViewById(R.id.tv_sch);
    }

    public void setSchedule(String sch){
        et_sch.setText(sch);
    }
}
