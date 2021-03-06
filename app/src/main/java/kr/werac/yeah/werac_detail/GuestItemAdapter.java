package kr.werac.yeah.werac_detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.werac.yeah.R;
import kr.werac.yeah.data.User;

/**
 * Created by dongja94 on 2016-01-18.
 */
public class GuestItemAdapter extends RecyclerView.Adapter<GuestItemHolder> {


    List<User> Guests_items = new ArrayList<User>();

    public void addAll(List<User> Guests_items){
        this.Guests_items.addAll(Guests_items);
        notifyDataSetChanged();
    }

    public void clear() {
        Guests_items.clear();
        notifyDataSetChanged();
    }

    GuestItemHolder.OnItemClickListener mListener;
    public void setOnItemClickListener(GuestItemHolder.OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public GuestItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_detail_guest_image, parent, false);
        return new GuestItemHolder(view);
    }

    @Override
    public void onBindViewHolder(GuestItemHolder holder, int position) {
        holder.setGuest_item(Guests_items.get(position).getProfile_image());//Guests_items.get(position));
//        holder.setOnItemClickListener(mListener);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if(Guests_items.size() != 0)
            return Guests_items.size();
        else
            return 0;
    }
}
