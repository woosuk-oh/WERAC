package kr.werac.yeah.werac_modify;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

    public ModifyScheduleHolder(View itemView) {
        super(itemView);
        rv_edit_schedule = (RecyclerView)itemView.findViewById(R.id.rv_edit_schedule);
    }

    public void setSchedule(List<String> schedules){
        mAdapter = new MScheduleItemAdapter();
        rv_edit_schedule.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(MyApplication.getContext(), LinearLayoutManager.VERTICAL, false);
        rv_edit_schedule.setLayoutManager(mLayoutManager);

        mAdapter.addAll(schedules);
    }
}
