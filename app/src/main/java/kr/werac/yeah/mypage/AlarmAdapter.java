package kr.werac.yeah.mypage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.werac.yeah.R;
import kr.werac.yeah.data.Alarm;
import kr.werac.yeah.data.WeracItem;

/**
 * Created by Tacademy on 2016-06-08.
 */
public class AlarmAdapter extends RecyclerView.Adapter<AlarmHolder> {

    List<Alarm> alarms = new ArrayList<>();

    public void clear() {
        alarms.clear();
        notifyDataSetChanged();
    }

    public void equalAll(List<Alarm> alarmList) {
        alarms = alarmList;
        notifyDataSetChanged();
    }

    AlarmHolder.OnItemClickListener mListener;
    public void setOnItemClickListener(AlarmHolder.OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public AlarmHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_alarm, parent, false);
        return new AlarmHolder(view);
    }

    @Override
    public void onBindViewHolder(AlarmHolder holder, int position) {
        holder.setAlram(alarms.get(position));
        holder.setOnItemClickListener(mListener);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return alarms.size();
    }
}
