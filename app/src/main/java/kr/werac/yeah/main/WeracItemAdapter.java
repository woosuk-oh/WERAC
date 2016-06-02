package kr.werac.yeah.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongja94 on 2016-01-18.
 */
public class WeracItemAdapter extends RecyclerView.Adapter<WeracItemHolder> {


    List<WeracItem> weracs = new ArrayList<WeracItem>();

    public void clear() {
        weracs.clear();
        notifyDataSetChanged();
    }

    public void add(WeracItem Werac) {
        weracs.add(Werac);
        notifyDataSetChanged();
    }

    public void addAll(List<WeracItem> WeracList) {
        weracs.addAll(WeracList);
        notifyDataSetChanged();
    }

    public void equalAll(List<WeracItem> WeracList) {
        weracs = WeracList;
        notifyDataSetChanged();
    }

    WeracItemHolder.OnItemClickListener mListener;
    public void setOnItemClickListener(WeracItemHolder.OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public WeracItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_werac, parent, false);
        return new WeracItemHolder(view);
    }

    @Override
    public void onBindViewHolder(WeracItemHolder holder, int position) {
        holder.setmWerac(weracs.get(position));
        holder.setOnItemClickListener(mListener);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return weracs.size();
    }
}
