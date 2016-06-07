package kr.werac.yeah.werac_detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.werac.yeah.R;

public class ScheduleItemAdapter extends RecyclerView.Adapter<ScheduleItemHolder> {


    List<String> sch_itmes = new ArrayList<String>();

    public void addAll(List<String> sch_itmes){
        if(sch_itmes != null)
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

    @Override
    public ScheduleItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_detail_schedule_text, parent, false);
        return new ScheduleItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ScheduleItemHolder holder, int position) {
        holder.setmSch_item(sch_itmes.get(position), position, sch_itmes.size());
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
