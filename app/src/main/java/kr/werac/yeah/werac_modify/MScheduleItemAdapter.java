package kr.werac.yeah.werac_modify;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.werac.yeah.R;

/**
 * Created by dongja94 on 2016-01-18.
 */
public class MScheduleItemAdapter extends RecyclerView.Adapter<MScheduleItemHolder> {


    List<String> sch_itmes = new ArrayList<String>();

    public void addAll(List<String> sch_itmes){
        this.sch_itmes.addAll(sch_itmes);
        notifyDataSetChanged();
    }

    public void clear() {
        sch_itmes.clear();
        notifyDataSetChanged();
    }

    public void add(String sch_item) {
        sch_itmes.add(sch_item);
        notifyDataSetChanged();
    }

    MScheduleItemHolder.OnItemClickListener mListener;
    public void setOnItemClickListener(MScheduleItemHolder.OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public MScheduleItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_create_schedule_text, parent, false);
        return new MScheduleItemHolder(view);
    }

    @Override
    public void onBindViewHolder(MScheduleItemHolder holder, int position) {
        holder.setmSch_item(sch_itmes.get(position));
        holder.setOnItemClickListener(mListener);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return sch_itmes.size();
    }
}
