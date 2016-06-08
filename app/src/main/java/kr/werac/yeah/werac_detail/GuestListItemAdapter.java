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
 * Created by Tacademy on 2016-06-08.
 */
public class GuestListItemAdapter extends RecyclerView.Adapter<GuestListItemHolder> {

    List<User> guests = new ArrayList<>();

    public void clear() {
        guests.clear();
        notifyDataSetChanged();
    }

    public void equalAll(List<User> guestList) {
        guests = guestList;
        notifyDataSetChanged();
    }

    @Override
    public GuestListItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_guests_list_item, parent, false);
        return new GuestListItemHolder(view);
    }

    @Override
    public void onBindViewHolder(GuestListItemHolder holder, int position) {
        holder.setGuest(guests.get(position));
    }

    @Override
    public int getItemCount() {
        return guests.size();
    }
}
