package kr.werac.yeah.werac_detail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import kr.werac.yeah.MyApplication;
import kr.werac.yeah.R;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class DetailSchedulesHolder extends RecyclerView.ViewHolder {

    RecyclerView rv_schedules;
    ScheduleItemAdapter mAdapter;
    LinearLayoutManager mLayoutManager;

    public DetailSchedulesHolder(View itemView) {
        super(itemView);
        rv_schedules = (RecyclerView)itemView.findViewById(R.id.rv_detail_schedule);
    }

    public void setSchedule(List<String> schedules){
        mAdapter = new ScheduleItemAdapter();
        rv_schedules.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(MyApplication.getContext(), LinearLayoutManager.VERTICAL, false);
        rv_schedules.setLayoutManager(mLayoutManager);

        mAdapter.addAll(schedules);
    }

}
