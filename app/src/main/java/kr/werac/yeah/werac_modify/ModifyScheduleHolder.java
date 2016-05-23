package kr.werac.yeah.werac_modify;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;
import kr.werac.yeah.MyApplication;
import kr.werac.yeah.R;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class ModifyScheduleHolder extends RecyclerView.ViewHolder {

    RecyclerView rv_edit_schedule;
    MScheduleItemAdapter mAdapter;
    LinearLayoutManager mLayoutManager;
    Button btn_add_sch;

    public ModifyScheduleHolder(View itemView) {
        super(itemView);
        rv_edit_schedule = (RecyclerView)itemView.findViewById(R.id.rv_edit_schedule_create);
        btn_add_sch = (Button)itemView.findViewById(R.id.btn_add_sch);
    }

    public void setSchedule(List<String> schedules){
        mAdapter = new MScheduleItemAdapter();
        rv_edit_schedule.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(MyApplication.getContext(), LinearLayoutManager.VERTICAL, false);
        rv_edit_schedule.setLayoutManager(mLayoutManager);
        btn_add_sch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.add("");
            }
        });

        mAdapter.addAll(schedules);
    }
}
